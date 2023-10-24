import { Component, EventEmitter, Output } from '@angular/core';

import { DiscussionService } from 'src/app/services/discussion.service';

@Component({
  selector: 'app-new-conv-popup',
  templateUrl: './new-conv-popup.component.html',
  styleUrls: ['./new-conv-popup.component.css']
})
export class NewConvPopupComponent {
   @Output() new_conv_popup = new EventEmitter<void>();

   newDiscussionTitle: string = '';

   constructor(private discussionService: DiscussionService) {}

  hide_new_conv_popup() {
    this.new_conv_popup.emit();
  }

  addDiscussion(): void {
    this.discussionService.addDiscussion(this.newDiscussionTitle);
    this.newDiscussionTitle = '';
  }
}
