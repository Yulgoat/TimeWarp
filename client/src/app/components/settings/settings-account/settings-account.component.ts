import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SigninServiceService } from 'src/app/services/signin-service.service';
import{SignOutService} from 'src/app/services/signout.service';

interface UserNameDTO {
  user_name: string;
}

@Component({
  selector: 'app-settings-account',
  templateUrl: './settings-account.component.html',
  styleUrls: ['./settings-account.component.css']
})
export class SettingsAccountComponent {
  account_picture : string = "/assets/icons/pp_contact1.jpg";

  textContent = "Pierre";
  isEditing = false;

  actual_user : UserNameDTO = {   //Object to get the current user
    user_name:''
  };

  none_actual_user : UserNameDTO = {  //Object to put to default the current user
    user_name:''
  };

  constructor(private router:Router, private signinService: SigninServiceService, private signoutService : SignOutService){
    //Retrieve the current user
    this.actual_user = this.signinService.actual_user;
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
    this.signoutService.signOut(this.actual_user).subscribe(
      (response) => {
        /* Post returns a success (code 200) */
        if (response.status === 200)           
          console.log('Successful Login');    
          this.signinService.setActualUser(this.none_actual_user); /* We put the current user to a default user */
          this.navigateToLogin();      
      },
      (error) => {
          console.log('Error Signout');          
      }
      );

  }


}
