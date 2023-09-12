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




}
