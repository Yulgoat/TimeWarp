import { Component, ElementRef, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { DiscussionService } from 'src/app/services/discussion.service';
import { UserService } from 'src/app/services/user.service';
import { Discussion } from 'src/app/models/discussion';
import { Message } from 'src/app/models/message';
import { User } from 'src/app/models/user';
import { Subject, delay } from 'rxjs';
import { UserSettingsService } from 'src/app/services/user-settings.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  // Arrays to store discussions and messages
  discussions: Discussion[] = [];
  messages: Message[] = [];

  // Variables to store input values and selected discussion
  newDiscussionUsername = '';
  selectedDiscussionId = '';
  newMessageContent = '';

  // Variables for user information
  loggedUser = ''; // The username of the currently logged-in user
  recipient = ''; // The recipient for new messages

  // Subject to stop polling messages on component destruction
  private stopPolling = new Subject<void>();

  // Constructor with dependency injection
  constructor(
    private router: Router,
    private discussionService: DiscussionService,
    private userService: UserService,
    private elementRef: ElementRef,
    public userSettingsService: UserSettingsService
  ) {
    // Initialize discussions with data from the service
    this.discussions = this.discussionService.discussions;
  }

  // Lifecycle hook - executed after the component is initialized
  ngOnInit() {
    // Fetch user settings from the server
    this.userSettingsService.getUserSettings();

    this.discussionService.discussions.length=0;
    this.discussionService.selectedDiscussionId='';
    // Fetch discussions from the service for the logged-in user
    this.discussionService.getDiscussions().subscribe({
      next: (discussions) => {
        discussions.forEach((discussion) => {
          this.discussions.unshift(discussion);
        });
        this.discussions.sort((d1, d2) => d2.timestamp - d1.timestamp);
      },
      error: (e) => console.error('An error has occurred for getDiscussions: ', e),
      complete: () => console.info('Get discussions complete')
    });

    // Get the current user
    this.userService.getCurrentUser().subscribe({
      next: (user: User) => {
        this.loggedUser = user.username;
      },
      error: (e) => console.error('An error has occurred for getCurrentUser: ', e)
    });

    // Start polling new messages and updating discussions
    this.discussionService.startPollingNewMessages(this.stopPolling).subscribe({
      next: (response) => {
        if(response){
          setTimeout(() => {
            this.scrollToBottom();
          });
        }
      },
      error: (e) => console.error('Error getMessage: ', e)
    });
  }

  // Lifecycle hook - executed just before Angular destroys the component
  ngOnDestroy(): void {
    // Stop polling messages when the component is destroyed
    this.stopPolling.next(void 0);
  }

  // Redirect to the settings page
  homeToSettings(): void {
    this.router.navigate(['/settings']);
  }

  /* ----- New Conversation Popup Management ----- */

  // Variable to control the visibility of the new conversation popup
  new_conv_popup = false;

  // Display the new conversation popup
  display_new_conv_popup() {
    this.new_conv_popup = true;
  }

  // Hide the new conversation popup
  hide_new_conv_popup() {
    this.new_conv_popup = false;
  }
  /* --------------------------------------------- */

  // Function to create a new discussion
  createDiscussion(): void {
    this.discussionService.createDiscussion(this.loggedUser, this.newDiscussionUsername).subscribe(
      {
        next: (response) => {
          // Select discussion created
          this.selectedDiscussionId = this.discussionService.selectedDiscussionId = response.body.id;
          // Clear messages for the selected discussion
          this.messages = this.discussionService.messages = [];
          // Set the new recipient
          this.recipient = this.newDiscussionUsername;
          this.newDiscussionUsername = ''; // Clear the input field after creating a discussion
        },
        error: (e) => console.error('Error createDiscussion: ', e),
        complete: () => console.info('Create discussion complete')
      }
    );
  }

  // Select a discussion and set the recipient based on user1 and user2
  selectDiscussion(discussionId: string, user1: string, user2: string): void {
    this.selectedDiscussionId = this.discussionService.selectedDiscussionId = discussionId;
    if (user1 == this.loggedUser) this.recipient = user2;
    else this.recipient = user1;
    this.discussionService.updateUnreadMessage(discussionId, false);

    // Clear and load messages for the selected discussion
    this.messages = this.discussionService.messages = [];
    this.discussionService.getMessages(this.selectedDiscussionId).subscribe({
      next: (messages) => {
        messages.forEach((message) => {
          this.messages.push(message);
        });
        this.messages.sort((m1, m2) => m1.timestamp - m2.timestamp);
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
    if (this.recipient != '' && this.newMessageContent!='') {
      this.discussionService.postMessage(this.recipient, this.newMessageContent).subscribe({
        error: (e) => console.error('Error postMessage: ', e),
        complete: () => {
          setTimeout(() => {
            this.scrollToBottom();
          }); // Without the delay (of 0 here), it does not take into account the last message
          console.info('PostMessage complete');
        }
      });
      this.newMessageContent = ''; // Clear the input field after posting a message
    }
  }

  // Scroll to the bottom of the messages container
  scrollToBottom() {
    const messagesContainer = this.elementRef.nativeElement.querySelector('.home_conversation_messages');
    if (messagesContainer) {
      messagesContainer.scrollTop = messagesContainer.scrollHeight;
    }
  }

  // Placeholder URLs for profile pictures and contact images
  ownprofilpicture = '../../../assets/images/pp_user1.jpg';
}
