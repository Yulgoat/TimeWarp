import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent {

  constructor(private router:Router){}

  username : string = "";
  email : string = "";
  password : string = "";
  confirmPwd : string ="";

  popUpErrorDisplay : boolean = false;
  popUpError : string ="";

  UserDTO : JSON = <JSON><unknown>{}

  navigateToLogin(){
    this.router.navigate(['/login']);
  }

  samePwd():boolean{
    return this.password===this.confirmPwd;
  }

  validation_email() : boolean{
    let sampleRegEx: RegExp = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return sampleRegEx.test(this.email);
  }


  createAccountFonction() : void{
    if(this.samePwd()){
      if(this.validation_email()){
        this.UserDTO = <JSON><unknown>{
          "username": this.username,
          "email" : this.email,
          "password": this.password
        }
        console.log(this.UserDTO);
      }
      else{
        this.popUpError = "Not a correct Email";
        this.popUpErrorDisplay = true;
        console.log(this.popUpError);
      }
    }
    else{
      this.popUpError = "Not the same Password";
      this.popUpErrorDisplay = true;
      console.log(this.popUpError);
    }
  }

  hide_popUpError() {
    this.popUpErrorDisplay = false;
  }



}
