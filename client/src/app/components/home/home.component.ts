import { Component, ElementRef} from '@angular/core';
import { Router } from '@angular/router';

import { DiscussionService } from 'src/app/services/discussion.service';
import { Discussion } from 'src/app/models/discussion';
import { Message } from 'src/app/models/message';
import { delay } from 'rxjs';

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

  loggedUser: string = 'alice';

  constructor(private router:Router, private discussionService: DiscussionService, private elementRef: ElementRef){
    this.discussions=this.discussionService.discussions;
    this.discussionService.getDiscussions(this.loggedUser).subscribe(
      {
        next: (discussions) => {
          discussions.forEach((discussion) => {
            this.discussions.unshift(discussion);
          });
        },
        error: (e) => console.error('An error has occured: ', e),
        complete: () => console.info('Get discussions complete')
      }
    );
  }

  homeToSettings() : void{
    this.router.navigate(['/settings']);
  }

  /* ----------------------------------------------------------------------------------------------------------------------------------------- */
  new_conv_popup : boolean = false;


  display_new_conv_popup() {
    this.new_conv_popup = true;
  }
  hide_new_conv_popup() {
    this.new_conv_popup = false;
  }
  /* ----------------------------------------------------------------------------------------------------------------------------------------- */


  selectDiscussion(discussionId: string): void {
    this.selectedDiscussionId = discussionId;
    this.messages = this.discussionService.getMessage(this.selectedDiscussionId);

    setTimeout(() => {
      this.scrollToBottom();
    });
  }

  addMessage(): void {
    if (this.selectedDiscussionId!='') {
      this.discussionService.addMessage(this.selectedDiscussionId, this.newMessageContent, true);
      this.newMessageContent = '';
    }

    setTimeout(() => {
      this.scrollToBottom();
    }); //without the delay (of 0 here) it does not take into account the last message
  }

  scrollToBottom() {
    const messagesContainer = this.elementRef.nativeElement.querySelector('.home_conversation_messages');
    if (messagesContainer) {
      messagesContainer.scrollTop = messagesContainer.scrollHeight;
    }
  }
  

  ownprofilpicture : string = "../../../assets/icons/pp_user1.jpg";
  contact1 : string = "../../../assets/icons/pp_contact1.jpg";
  contact2 : string = "../../../assets/icons/pp_contact2.jpg";

  
}
