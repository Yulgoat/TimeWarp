import { Component, EventEmitter, Output } from '@angular/core';
import { User } from 'src/app/models/user';
import { DiscussionService } from 'src/app/services/discussion.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-new-conv-popup',
  templateUrl: './new-conv-popup.component.html',
  styleUrls: ['./new-conv-popup.component.css']
})
export class NewConvPopupComponent {
   @Output() new_conv_popup = new EventEmitter<void>();

   newDiscussionUsername: string = ''; // Variable to store the new discussion's username
   loggedUser: string = '';

   constructor(private discussionService: DiscussionService, private userService: UserService) {
    this.userService.getCurrentUser().subscribe({
      next: (user: User) => {
        this.loggedUser = user.username;
      },
      error: (e) => {
        console.error('An error has occurred for getCurrentUser : ', e);
      }
    });
   }

   // Function to hide the new conversation popup
   hide_new_conv_popup() {
     this.new_conv_popup.emit();
   }

   // Function to create a new discussion
   createDiscussion(): void {
     this.discussionService.createDiscussion(this.loggedUser, this.newDiscussionUsername).subscribe(
       {
         error: (e) => console.error('Error createDiscussion: ', e), // Log any error that occurs during discussion creation
         complete: () => console.info('Create discussion complete') // Log when the discussion creation is complete
       }
     );
     this.newDiscussionUsername = ''; // Clear the input field after creating a discussion
   }
}
