// discussion.service.ts
import { Injectable } from '@angular/core';
import { Discussion } from '../models/discussion';
import { Message } from '../models/message';

import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscussionService {

  private baseUrl = 'http://localhost:4200/serverapi/discussions';

  discussions: Discussion[] = [];
  messages: Message[] = [];

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
  getMessage(discussionId: string): Observable<Message[]> {
    return this.http.get<Message[]>(this.baseUrl + '/' + discussionId + '/messages');
  }
}
