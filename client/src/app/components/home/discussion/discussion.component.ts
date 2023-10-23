// discussion.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DiscussionService } from 'src/app/services/discussion.service';
import { Discussion } from 'src/app/models/discussion';

@Component({
  selector: 'app-discussion',
  templateUrl: './discussion.component.html'
})

export class DiscussionComponent{
  discussionId: number;
  discussion: Discussion | undefined;
  newMessageContent: string = '';

  constructor(private route: ActivatedRoute, private discussionService: DiscussionService) {
    this.discussionId = parseInt(route.snapshot.paramMap.get('id') || '0');
    this.discussion = this.discussionService.discussions.find(d => d.id === this.discussionId);
  }

  addMessage(): void {
    if (this.discussion) {
      this.discussionService.addMessage(this.discussion.id, this.newMessageContent);
      this.newMessageContent = '';
    }
  }
}
