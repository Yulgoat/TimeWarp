import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-settings-account',
  templateUrl: './settings-account.component.html',
  styleUrls: ['./settings-account.component.css']
})
export class SettingsAccountComponent {
  account_picture : string = "/assets/icons/pp_contact1.jpg";

  textContent = "Pierre";
  isEditing = false;

  constructor(private router:Router){}
  
  @Output() go_chg_pwd = new EventEmitter<void>();

  navigateToChgPwd() : void{
    this.go_chg_pwd.emit();
  }

  navigateToLogin() : void{
    this.router.navigate(['/login']);
  }



}
