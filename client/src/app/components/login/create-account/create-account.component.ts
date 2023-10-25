import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SignupServiceService } from 'src/app/services/signup-service.service';


@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent {

  constructor(private router:Router, private signupService: SignupServiceService){}

  username : string = "";
  email : string = "";
  password : string = "";
  confirmPwd : string ="";

  UserDTO : JSON = <JSON><unknown>{}   // Json which contains the information that will be sent to the server

  /* Message that will display the corresponding field in case of error */
  userErrorMessage : string ="";
  emailErrorMessage : string ="";
  pwdErrorMessage : string ="";
  cfpwdErrorMessage : string ="";

  /* Will be true if the corresponding field contain an error, else false */
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

  /* Checks if the different fields are empty */
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

  /* Update the username field with "User already exists", obtained in the post request */
  username_exist() : void {
    this.username = ''; 
    this.userError = true;
    this.userErrorMessage = "Username Already Exists";
  }

  /* Update the Email field with "Email already exists", obtained in the post request */
  email_exist() : void {
    this.email = '';  
    this.emailError = true;
    this.emailErrorMessage = "Email Already Exists";
  }


  /* Launches the post request and processes the different server returns */
  signUp(user: JSON): void {
    this.signupService.signup(user).subscribe(
      /* Classic Response */ 
      (response) => {
        /* Post returns a success (code 200) */
        if (response.status === 200) {
          console.log('Successful registration');
          this.navigateToLogin();
        }
        /* Post returns an error (code 409). Here it is if the post returns the error as a classic return and not as an error  */
        else if (response.status === 409) {
          if (response.error && response.error.message) {
            if (response.error.message === 'Username already exists') {
              this.username_exist();
            } 
            else if (response.error.message === 'Email already exists') {
              this.email_exist();
            }
          }
        }
      },
      /* Errors */ 
      (error) => {
        /* Post returns an error (code 409). Here it is if the post returns the error as an error */
        if (error.status === 409) {
          if (error.error && error.error.message) {
            /* See if the error message is linked to an already existing email or username, and do the fonction */
            if (error.error.message === 'Username already exists') {
              this.username_exist();
            } 
            else if (error.error.message === 'Email already exists') {
              this.email_exist();
            }
          }
        }
      }
    );
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
      this.signUp(this.UserDTO);
      
    }
  }


}
