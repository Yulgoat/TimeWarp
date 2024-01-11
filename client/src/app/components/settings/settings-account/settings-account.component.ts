import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { SigninServiceService } from 'src/app/services/signin-service.service';
import{SignOutService} from 'src/app/services/signout.service';
import { UserSettingsService } from 'src/app/services/user-settings.service';

interface UserNameDTO {
  user_name: string;
}

@Component({
  selector: 'app-settings-account',
  templateUrl: './settings-account.component.html',
  styleUrls: ['./settings-account.component.css']
})
export class SettingsAccountComponent {
  account_picture  = "/assets/images/pp_user1.jpg";


  actual_user : UserNameDTO={ // Object which contains the current user 
    user_name: ''
  };

  actual_username = "TempUser";
  isEditing = false;

  username_already_exists = false;


  constructor(private router:Router, private signinService: SigninServiceService, private signoutService : SignOutService, private userSettingsService: UserSettingsService){
    this.signinService.getActualUser().subscribe(actual_user => {
      if (actual_user.user_name !== '') {
        const username = actual_user.user_name;
        const regex = /^([^@]+)/;
        const match = username.match(regex);    
        if (match && match[1]) {
          this.actual_username = match[1];
        } else {
          console.error("Aucune correspondance trouvée pour le nom d'utilisateur.");
        }
      }
    });
  }
  
  @Output() go_chg_pwd = new EventEmitter<void>();

  navigateToChgPwd() : void{
    this.go_chg_pwd.emit();
  }

  navigateToLogin() : void{
    this.router.navigate(['/login']);
  }


  /* Disconnect the current user */
  disconnect() : void{
    this.signoutService.signOut().subscribe();
    this.signinService.setActualUserToDefault();          //Set the current user to default beacause there is no user
    this.navigateToLogin(); 
  }


  /* Change Username */
  chg_username() : void{
    this.actual_username = this.actual_username.toLowerCase();
    const UserDTO = <JSON><unknown>{
    "username": this.actual_username,
    "email" : "",
    "password": ""
  }          

    this.userSettingsService.change_username(UserDTO).subscribe(
      (response) => {
        /* Post returns a success (code 200) */
        if (response.status === 200) {
          console.log('Success');
          this.disconnect();
        }
        else if (response.status === 409) {
          console.log('error');
          this.username_already_exists = true;
        }
      },
      (error) => {
        /* Post returns an error (code 409). Here it is if the post returns the error as an error */
        if (error.status === 409) {
          console.log('error');
          this.username_already_exists = true;
        }
      }
    );
  }

  
}
