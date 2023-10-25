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

  userOK = true;
  emailOK = true;
  pwdOk = true;
  CfmPwdOK = true;

  EmptyUser = false;
  EmptyEmail = false;
  EmptyPwd = false;
  EmptyCfmPwd = false;


  /* Router To Login*/
  navigateToLogin(){
    this.router.navigate(['/login']);
  }

  /* Verify that password and confirmPwd are identical */
  samePwd():boolean{
    return this.pwdOk=this.CfmPwdOK=(this.password===this.confirmPwd);
  }

  /* Using a regex we check if what the user enters in email looks like an email address */
  validation_email() : boolean{
    let sampleRegEx: RegExp = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return this.emailOK=(sampleRegEx.test(this.email));
  }

  username_empty (): boolean{
    if(this.username==="") {this.EmptyUser = true; this.userOK = false; return true;}
    else{this.EmptyUser = false; this.userOK = true; return false;}
  }
  email_empty (): boolean{
    if(this.email==="") {this.EmptyEmail = true; this.emailOK = false; return true;}
    else{this.EmptyEmail = false; this.emailOK = true; return false;}
  }
  pwd_empty (): boolean{
    if(this.password==="") {this.EmptyPwd = true; this.pwdOk = false; return true;}
    else{this.EmptyPwd = false; this.pwdOk = true; return false;}
  }
  cfmPwd_empty (): boolean{
    if(this.confirmPwd==="") {this.EmptyCfmPwd = true; this.CfmPwdOK = false; return true;}
    else{this.EmptyCfmPwd = false; this.CfmPwdOK = true; return false;}
  }


  /* Main Fonction */
  createAccountFonction() : void{
    let isUsernameEmpty = this.username_empty();
    let isEmailEmpty = this.email_empty();
    let isPwdEmpty = this.pwd_empty();
    let isCfmPwdEmpty = this.cfmPwd_empty();
    
    if(!isUsernameEmpty||!isEmailEmpty||!isPwdEmpty||!isCfmPwdEmpty){
      let isPasswordValid = this.samePwd();
      let isEmailValid = this.validation_email();
  
      if (isPasswordValid && isEmailValid) {
          this.UserDTO = <JSON><unknown>{
            "username": this.username,
            "email" : this.email,
            "password": this.password
          }          
          console.log(this.UserDTO);
          this.navigateToLogin();
      }
      else{
        console.log("Email or password problem");
      }
    }  

    if (!this.userOK) 
      this.username = ''; // Réinitialisez la valeur du champ username    
    if (!this.emailOK)
      this.email = ''; // Réinitialisez la valeur du champ email     
    if (!this.pwdOk) 
      this.password = ''; // Réinitialisez la valeur du champ password    
    if (!this.CfmPwdOK) 
      this.confirmPwd = ''; // Réinitialisez la valeur du champ confirmPwd  
  }



}
