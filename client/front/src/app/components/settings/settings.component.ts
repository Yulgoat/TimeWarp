import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent {

  constructor(private router:Router){}

  settingsToHome() : void{
    this.router.navigate(['/home']);
  }

  settingsToLogin() : void{  
    this.router.navigate(['/login'])  
  }

  /* ----------------------------------------------------------------------------------------------------------------------------------------- */
account : boolean = false;
notifications : boolean = false;
appearance : boolean = false;
language : boolean = false;
acknowlodgements : boolean = false;

display_account_settings() {
  this.account = true;
  this.notifications = false;
  this.appearance = false;
  this.language = false;
  this.acknowlodgements = false;
}

display_notifications_settings(){
  this.account = false;
  this.notifications = true;
  this.appearance = false;
  this.language = false;
  this.acknowlodgements = false;
}

display_appearance_settings(){
  this.account = false;
  this.notifications = false;
  this.appearance = true;
  this.language = false;
  this.acknowlodgements = false;
}

display_language_settings(){
  this.account = false;
  this.notifications = false;
  this.appearance = false;
  this.language = true;
  this.acknowlodgements = false;
}

display_acknowlodgements_settings(){
  this.account = false;
  this.notifications = false;
  this.appearance = false;
  this.language = false;
  this.acknowlodgements = true;
}



}
