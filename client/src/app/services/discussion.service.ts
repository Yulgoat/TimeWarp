// discussion.service.ts
import { Injectable } from '@angular/core';
import { Discussion } from '../models/discussion';
import { Message } from '../models/message';

import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscussionService {

  private baseUrl = 'http://localhost:8080/discussions';

  discussions: Discussion[] = [];

  constructor(private http: HttpClient) { }

  createDiscussion(loggedUser: string, recipient: string): Observable<any> {
    const users : any = {
      "user1": loggedUser,
      "user2": recipient
    };

    return this.http.post(this.baseUrl + '/create', users, { observe: 'response' }).pipe(
      tap((response: HttpResponse<any>) => {
        if (response.status === 201) {
          const discussion = new Discussion(response.body.id, loggedUser, recipient);
          this.discussions.unshift(discussion);
        }
      })
    );
  }

  getDiscussions(loggedUser: string): Observable<Discussion[]> {
    return this.http.get<Discussion[]>(this.baseUrl+'/'+loggedUser);
  }

  addMessage(discussionId: string, messageContent: string, sentByMe: boolean): void {
    const discussion = this.discussions.find(d => d.id === discussionId);
    if (discussion) {
      const message = new Message(discussion.messages.length, messageContent, sentByMe, new Date);
      discussion.messages.push(message);
    }
  }

  getMessage(discussionId: string): Message[]{
    const discussion = this.discussions.find(d => d.id === discussionId);
    return discussion ? discussion.messages : [];
  }
}
