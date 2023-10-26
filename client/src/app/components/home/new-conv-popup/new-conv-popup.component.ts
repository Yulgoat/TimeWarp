import { Component, EventEmitter, Output } from '@angular/core';

import { DiscussionService } from 'src/app/services/discussion.service';

@Component({
  selector: 'app-new-conv-popup',
  templateUrl: './new-conv-popup.component.html',
  styleUrls: ['./new-conv-popup.component.css']
})
export class NewConvPopupComponent {
   @Output() new_conv_popup = new EventEmitter<void>();

   newDiscussionUsername: string = '';

   constructor(private discussionService: DiscussionService) {}

  hide_new_conv_popup() {
    this.new_conv_popup.emit();
  }

  createDiscussion(): void {
    this.discussionService.createDiscussion("alice", this.newDiscussionUsername).subscribe( //TODO: Remplacer alice
      {
        error: (e) => console.error('Error createDiscussion: ', e),
        complete: () => console.info('Create discussion complete')
      }
    );
    console.info('ok c bon');
    this.newDiscussionUsername = '';
  }
}
