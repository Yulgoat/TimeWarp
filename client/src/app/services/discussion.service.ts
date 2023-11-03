// discussion.service.ts
import { Injectable } from '@angular/core';
import { Discussion } from '../models/discussion';
import { Message } from '../models/message';

import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map, repeat, retry, share, takeUntil, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscussionService {

  private baseUrl = 'http://localhost:4200/serverapi/discussions';

  discussions: Discussion[] = [];
  messages: Message[] = [];

  selectedDiscussionId: string = '';

  constructor(private http: HttpClient) { }

  // Create a new discussion between two users
  createDiscussion(loggedUser: string, recipient: string): Observable<any> {

    return this.http.post(this.baseUrl + '/create', recipient, { observe: 'response' }).pipe(
      tap((response: HttpResponse<any>) => {
        if (response.status === 201) {
          const discussion = new Discussion(response.body.id, loggedUser, recipient);
          this.discussions.unshift(discussion); // Add the new discussion to the beginning of the list
        }
      })
    );
  }

  // Get discussions for a specific user
  getDiscussions(): Observable<Discussion[]> {
    return this.http.get<Discussion[]>(this.baseUrl);
  }

  // Post a new message to a discussion
  postMessage(to: string, body: string): Observable<any> {
    const postMessageDTO: any = {
      "to": to,
      "type": "text/plain",
      "body": body
    };
    return this.http.post(this.baseUrl + '/message', postMessageDTO, { observe: 'response' }).pipe(
      tap((response: HttpResponse<any>) => {
        if (response.status === 201) {
          const message = new Message(response.body.id, response.body.timestamp, response.body.from, response.body.to, response.body.type, response.body.body);
          this.messages.push(message); // Add the new message to the messages list
        }
      })
    );
  }

  // Get messages for a specific discussion
  getMessages(discussionId: string): Observable<Message[]> {
    return this.http.get<Message[]>(this.baseUrl + '/' + discussionId + '/messages');
  }

  /**
   * Start polling new messages and updating discussions.
   * @param stopPolling Control observable: any event sent on this observable stop the polling
   */
  startPollingNewMessages(stopPolling: Observable<void>) {

    // Poll the next new message
    this.http.get<Message>(this.baseUrl + '/message').pipe(
      map(message => {

        // If no message yet, nothing to do
        if (!message) return;

        // If message is sent by current user: search for an existing discussion with the receiver,
        // otherwise, if message is received by current user: search for an existing discussion with the sender
        let discussion = this.discussions.find(discussion => discussion.user1 === message.from || discussion.user2 === message.from);

        // If no discussion has been found, create a new one and add it to the array of discussions
        if (!discussion) {
          this.createDiscussion(message.to, message.from).subscribe(
          {
            error: (e) => console.error('Error createDiscussion: ', e), // Log any error that occurs during discussion creation
            complete: () => console.info('Create discussion complete') // Log when the discussion creation is complete
          }
        );
        }

        // Add the message to the discussion
        if(discussion?.id == this.selectedDiscussionId)
        this.messages.push(message);
      }),
      repeat(), // on success, repeat immediately
      retry({ delay: 1000 }), // on error, retry after 1s
      share(), // be sure to never duplicate this observable
      takeUntil(stopPolling) // stop polling if an event is sent on control observable
    ).subscribe();
  }
}
