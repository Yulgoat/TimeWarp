import { Component, ElementRef } from '@angular/core';
import { Router } from '@angular/router';

import { DiscussionService } from 'src/app/services/discussion.service';
import { Discussion } from 'src/app/models/discussion';
import { Message } from 'src/app/models/message';
import { Subject, delay } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  discussions: Discussion[] = [];
  messages: Message[] = [];
  selectedDiscussionId: string = '';
  newMessageContent: string = '';

  loggedUser: string = ''; // The username of the currently logged-in user
  recipient: string = ''; // The recipient for new messages

  private stopPolling = new Subject<void>();

  constructor(
    private router: Router,
    private discussionService: DiscussionService,
    private userService: UserService,
    private elementRef: ElementRef
  ) {
    // Initialize discussions with data from the service
    this.discussions = this.discussionService.discussions;
  }

  ngOnInit() {
    this.discussionService.discussions.length=0;
    // Fetch discussions from the service for the logged-in user
    this.discussionService.getDiscussions().subscribe({
      next: (discussions) => {
        discussions.forEach((discussion) => {
          this.discussions.unshift(discussion);
        });
      },
      error: (e) => console.error('An error has occurred for getDiscussions: ', e),
      complete: () => console.info('Get discussions complete')
    });

    this.userService.getCurrentUser().subscribe({
      next: (user: User) => {
        this.loggedUser = user.username;
      },
      error: (e) => {
        console.error('An error has occurred for getCurrentUser : ', e);
      }
    });

    // Start polling new messages and updating discussions
    this.discussionService.startPollingNewMessages(this.stopPolling);
  }

  ngOnDestroy(): void {

    // Stop polling messages
    this.stopPolling.next(void 0);
  }


  // Redirect to the settings page
  homeToSettings(): void {
    this.router.navigate(['/settings']);
  }

  /* ----- New Conversation Popup Management ----------------------------------------- */

  new_conv_popup: boolean = false;

  // Display the new conversation popup
  display_new_conv_popup() {
    this.new_conv_popup = true;
  }

  // Hide the new conversation popup
  hide_new_conv_popup() {
    this.new_conv_popup = false;
  }
  /* ----------------------------------------------------------------------------------------------------------------------------------------- */

  // Select a discussion and set the recipient based on user1 and user2
  selectDiscussion(discussionId: string, user1: string, user2: string): void {
    this.selectedDiscussionId = discussionId;
    if (user1 == this.loggedUser) this.recipient = user2;
    else this.recipient = user1;

    // Clear and load messages for the selected discussion
    this.messages = this.discussionService.messages = [];
    this.discussionService.getMessages(this.selectedDiscussionId).subscribe({
      next: (messages) => {
        messages.forEach((message) => {
          this.messages.push(message);
        });
      },
      error: (e) => console.error('An error has occurred: ', e),
      complete: () => {
        setTimeout(() => {
          this.scrollToBottom();
        });
        console.info('Get messages complete');
      }
    });
  }

  // Post a new message to the recipient
  postMessage(): void {
    if (this.recipient != '') {
      this.discussionService.postMessage(this.recipient, this.newMessageContent).subscribe({
        error: (e) => console.error('Error postMessage: ', e),
        complete: () => {
          setTimeout(() => {
            this.scrollToBottom();
          }); // Without the delay (of 0 here), it does not take into account the last message
          console.info('PostMessage complete');
        }
      });
      this.newMessageContent = '';
    }
  }

  // Scroll to the bottom of the messages container
  scrollToBottom() {
    const messagesContainer = this.elementRef.nativeElement.querySelector('.home_conversation_messages');
    if (messagesContainer) {
      messagesContainer.scrollTop = messagesContainer.scrollHeight;
    }
  }

  ownprofilpicture: string = '../../../assets/images/pp_user1.jpg';
  contact: string = '../../../assets/images/light_contact.svg';
}
