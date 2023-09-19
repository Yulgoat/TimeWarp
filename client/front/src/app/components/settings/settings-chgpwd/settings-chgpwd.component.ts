import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-settings-chgpwd',
  templateUrl: './settings-chgpwd.component.html',
  styleUrls: ['./settings-chgpwd.component.css']
})
export class SettingsChgpwdComponent {
  @Output() go_account = new EventEmitter<void>();

  navigateToAccount() : void{
    this.go_account.emit();
  }

}
