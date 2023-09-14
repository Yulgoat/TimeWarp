import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private router:Router){}

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

  ownprofilpicture : string = "../../../assets/icons/pp_user1.jpg";
  contact1 : string = "../../../assets/icons/pp_contact1.jpg";
  contact2 : string = "../../../assets/icons/pp_contact2.jpg";

  
}
