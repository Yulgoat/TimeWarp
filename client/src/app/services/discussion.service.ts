// discussion.service.ts
import { Injectable } from '@angular/core';
import { Discussion } from '../models/discussion';
import { Message } from '../models/message';

import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, distinctUntilChanged, map, repeat, retry, share, takeUntil, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscussionService {

  private baseUrl = '/serverapi/discussions';

  discussions: Discussion[] = [];
  messages: Message[] = [];

  selectedDiscussionId = '';

  private messageNotification = new Audio('../../assets/sounds/message-sound.mp3');
  private messageSent = new Audio('../../assets/sounds/send-message.mp3');

  //default notifications settings
  soundParameter = true;
  badgeParameter = true;

  constructor(private http: HttpClient) { }

  /**
   * Create a new discussion between two users.
   * @param loggedUser The username of the currently logged-in user.
   * @param recipient The username of the recipient for the new discussion.
   */
  createDiscussion(loggedUser: string, recipient: string): Observable<any> {
    return this.http.post(this.baseUrl, recipient, { observe: 'response' }).pipe(
      tap((response: HttpResponse<any>) => {
        if (response.status === 201) {
          // Create a new Discussion object and add it to the discussions list
          const discussion = new Discussion(response.body.id, response.body.timestamp, loggedUser, recipient, false);
          this.discussions.unshift(discussion);
        }
      })
    );
  }

  /**
   * Get discussions for the currently logged-in user.
   */
  getDiscussions(): Observable<Discussion[]> {
    return this.http.get<Discussion[]>(this.baseUrl);
  }

  /**
   * Update the timestamp of a discussion and sort the list.
   * @param discussionId The ID of the discussion to update.
   */
  updateTimestampDiscussion(discussionId: string) {
    this.http.patch(this.baseUrl, discussionId, { observe: 'response' }).pipe(
      tap((response: HttpResponse<any>) => {
        if (response.status === 200) {
          const discussion = this.discussions.find((d) => d.id == discussionId);
          if (discussion) {
            discussion.timestamp = response.body;
          }
          this.discussions.sort((d1, d2) => d2.timestamp - d1.timestamp);
        }
      })
    ).subscribe({
      error: (e) => console.error('An error has occurred for updateTimestampDiscussion: ', e),
      complete: () => console.info('Update discussion timestamp complete')
    });
  }

  /**
   * Updates the unread message status for a specified discussion.
   *
   * @param {string} discussionId - The unique identifier of the discussion.
   * @param {boolean} unreadMessage - The new unread message status (true for unread, false for read).
   */
  updateUnreadMessage(discussionId: string, unreadMessage: boolean){
    const requestBody = {
      "discussionId": discussionId,
      "unreadMessage": unreadMessage
    }
    this.http.patch(`${this.baseUrl}/unreadmessage`, requestBody).subscribe({
      error: (e) => console.error('An error has occurred for updateUnreadMessage: ', e),
      complete: () => {
        const discussion = this.discussions.find(discussion => discussion.id == discussionId);
        if(discussion) discussion.unreadMessage = unreadMessage;
        console.info('Update unreadMessage complete')
      }
    });
  }

  /**
   * Post a new message to a discussion.
   * @param to The username of the recipient.
   * @param body The content of the message.
   */
  postMessage(to: string, body: string): Observable<any> {
    const postMessageDTO: any = {
      "to": to,
      "type": "text/plain",
      "body": body
    };
    return this.http.post(`${this.baseUrl}/message`, postMessageDTO, { observe: 'response' }).pipe(
      tap((response: HttpResponse<any>) => {
        if (response.status === 201) {
          // Create a new Message object and add it to the messages list
          const message = new Message(response.body.id, response.body.timestamp, response.body.from, response.body.to, response.body.type, response.body.body);
          this.messages.push(message);
          if(this.soundParameter) this.messageSent.play();
          // Update the timestamp of the current discussion
          this.updateTimestampDiscussion(this.selectedDiscussionId);
        }
      })
    );
  }

  /**
   * Get messages for a specific discussion.
   * @param discussionId The ID of the discussion to retrieve messages for.
   */
  getMessages(discussionId: string): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.baseUrl}/${discussionId}/messages`);
  }

  /**
   * Start polling new messages and updating discussions.
   * @param stopPolling Control observable: any event sent on this observable stops the polling.
   */
  startPollingNewMessages(stopPolling: Observable<void>): Observable<any> {

    // Poll the next new message
    return this.http.get<Message>(`${this.baseUrl}/message`).pipe(
      map(message => {

        // If no message yet, nothing to do
        if (!message) return;

        // If the message is sent by the current user, search for an existing discussion with the receiver;
        // otherwise, if the message is received by the current user, search for an existing discussion with the sender
        const discussion = this.discussions.find(discussion => discussion.user1 === message.from || discussion.user2 === message.from);

        // If no discussion has been found, create a new one and add it to the array of discussions
        if (!discussion) {
          this.createDiscussion(message.to, message.from).subscribe(
            {
              next: (response) => this.updateUnreadMessage(response.body.id, true),
              error: (e) => console.error('Error createDiscussion: ', e),
              complete: () => console.info('Create discussion complete')
            }
          );
        } else {
            // Update the timestamp of the existing discussion
            this.updateTimestampDiscussion(discussion.id);
            // Add the message to the discussion
            if (discussion.id == this.selectedDiscussionId){
              this.messages.push(message);
              this.updateUnreadMessage(discussion.id, false);
            }else{
              // Update the unreadMessage of the existing discussion
              this.updateUnreadMessage(discussion.id, true);
            }
        }
        if(this.soundParameter) this.messageNotification.play();
        return message;
      }),
      repeat(), // on success, repeat immediately
      retry({ delay: 1000 }), // on error, retry after 1s
      share(), // be sure to never duplicate this observable
      takeUntil(stopPolling) // stop polling if an event is sent on control observable
    );
  }
}
