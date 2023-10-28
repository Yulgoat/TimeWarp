import { Component, EventEmitter, Output } from '@angular/core';
import { DiscussionService } from 'src/app/services/discussion.service';

@Component({
  selector: 'app-new-conv-popup',
  templateUrl: './new-conv-popup.component.html',
  styleUrls: ['./new-conv-popup.component.css']
})
export class NewConvPopupComponent {
   @Output() new_conv_popup = new EventEmitter<void>();

   newDiscussionUsername: string = ''; // Variable to store the new discussion's username

   constructor(private discussionService: DiscussionService) {}

   // Function to hide the new conversation popup
   hide_new_conv_popup() {
     this.new_conv_popup.emit();
   }

   // Function to create a new discussion
   createDiscussion(): void {
     this.discussionService.createDiscussion("alice", this.newDiscussionUsername).subscribe( //TODO: Replace 'alice' with a dynamic value
       {
         error: (e) => console.error('Error createDiscussion: ', e), // Log any error that occurs during discussion creation
         complete: () => console.info('Create discussion complete') // Log when the discussion creation is complete
       }
     );
     this.newDiscussionUsername = ''; // Clear the input field after creating a discussion
   }
}
