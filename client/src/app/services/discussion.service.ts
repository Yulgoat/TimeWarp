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
    const discussion = new Discussion(this.discussions.length+1, titre);
    this.discussions.push(discussion);
  }

  getDiscussions(): Discussion[] {
    return this.discussions;
  }

  addMessage(discussionId: number, messageContenu: string): void {
    const discussion = this.discussions.find(d => d.id === discussionId);
    if (discussion) {
      const message = new Message(discussion.messages.length + 1, messageContenu);
      discussion.messages.push(message);
    }
  }
}
