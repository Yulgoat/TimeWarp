import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';

import { DiscussionService } from 'src/app/services/discussion.service';
import { Discussion } from 'src/app/models/discussion';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  discussions: Discussion[] = [];
  selectedDiscussionId: number | null = null;

  constructor(private router:Router, private discussionService: DiscussionService){
    this.discussions = this.discussionService.getDiscussions();
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


  selectDiscussion(discussionId: number): void {
    this.selectedDiscussionId = discussionId;
  }

  ownprofilpicture : string = "../../../assets/icons/pp_user1.jpg";
  contact1 : string = "../../../assets/icons/pp_contact1.jpg";
  contact2 : string = "../../../assets/icons/pp_contact2.jpg";

  
}
