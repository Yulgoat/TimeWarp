// discussion.service.ts
import { Injectable } from '@angular/core';
import { Discussion } from '../models/discussion';
import { Message } from '../models/message';

@Injectable({
  providedIn: 'root'
})
export class DiscussionService {
  discussions: Discussion[] = [];

  addDiscussion(titre: string): void {
    const discussion = new Discussion(this.discussions.length, titre);
    this.discussions.push(discussion);
  }

  getDiscussions(): Discussion[] {
    return this.discussions;
  }

  addMessage(discussionId: number, messageContent: string, sentByMe: boolean): void {
    const discussion = this.discussions.find(d => d.id === discussionId);
    if (discussion) {
      const message = new Message(discussion.messages.length, messageContent, sentByMe, new Date);
      discussion.messages.push(message);
    }
  }

  getMessage(discussionId: number): Message[]{
    const discussion = this.discussions.find(d => d.id === discussionId);
    return discussion ? discussion.messages : [];
  }
}
