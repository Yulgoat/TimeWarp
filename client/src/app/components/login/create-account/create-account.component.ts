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

  UserDTO : JSON = <JSON><unknown>{}            // Json which contains the information that will be sent to the server

  userErrorMessage : string ="";
  emailErrorMessage : string ="";
  pwdErrorMessage : string ="";
  cfpwdErrorMessage : string ="";

  userError : boolean = false;
  emailError : boolean = false;
  pwdError : boolean = false;
  cfpwdError : boolean = false;



  /* Router To Login*/
  navigateToLogin(){
    this.router.navigate(['/login']);
  }

  /* Verify that password and confirmPwd are identical */
  samePwd():boolean{
    return (this.password===this.confirmPwd);
  }

  /* Using a regex we check if what the user enters in email looks like an email address */
  validation_email() : boolean{
    let sampleRegEx: RegExp = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return sampleRegEx.test(this.email);
  }

  username_empty (): boolean{
    if(this.username==="") {this.userErrorMessage = "Field Empty";return true; }
    else{return false;}
  }
  email_empty (): boolean{
    if(this.email==="") {this.emailErrorMessage = "Field Empty";return true; }
    else{return false;}
  }
  pwd_empty (): boolean{
    if(this.password==="") {this.pwdErrorMessage = "Field Empty";return true; }
    else{return false;}
  }
  cfmPwd_empty (): boolean{
    if(this.confirmPwd==="") {this.cfpwdErrorMessage = "Field Empty";return true; }
    else{return false;}
  }


  /* Main Fonction */
  createAccountFonction() : void{
    if (this.username_empty()) this.userError = true; else this.userError = false;
    if (this.email_empty()) this.emailError = true; else this.emailError = false;
    if (this.pwd_empty()) this.pwdError = true; else this.pwdError = false;
    if (this.cfmPwd_empty()) this.cfpwdError = true; else this.cfpwdError = false;

    let isSamePassword = this.samePwd();
    let isCorrectEmail = this.validation_email();

    if(!this.pwdError && !isSamePassword){ this.pwdError = true; this.pwdErrorMessage = "Not the Same Password";}
    if(!this.cfpwdError && !isSamePassword){this.cfpwdError = true; this.cfpwdErrorMessage = "Not the Same Password";}
    if(!this.emailError&&!isCorrectEmail){this.emailError=true; this.emailErrorMessage = "Not a correct Email";}
   
    if(this.userError)
      this.username = ''; 
    if(this.emailError)
      this.email = '';    
    if(this.pwdError)
      this.password = '';  
    if(this.cfpwdError)
      this.confirmPwd = ''; 
    
    if (!this.userError&&!this.emailError&&!this.pwdError&&!this.cfpwdError){
      this.UserDTO = <JSON><unknown>{
        "username": this.username,
        "email" : this.email,
        "password": this.password
      }          
      console.log(this.UserDTO);
      this.navigateToLogin();
    }
  }



}
