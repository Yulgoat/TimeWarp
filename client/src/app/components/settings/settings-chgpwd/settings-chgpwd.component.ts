import { Component, EventEmitter, Output } from '@angular/core';
import { ChangePwdService } from 'src/app/services/change-pwd.service';

interface ChangePwdDTO {
  oldpassword: string;
  newpassword: string;
}

@Component({
  selector: 'app-settings-chgpwd',
  templateUrl: './settings-chgpwd.component.html',
  styleUrls: ['./settings-chgpwd.component.css']
})
export class SettingsChgpwdComponent {

  constructor(private changePwdService: ChangePwdService){}
  
  @Output() go_account = new EventEmitter<void>();


  oldPwd : string = "";
  newPwd : string = "";
  confirmNewPwd : string ="";
  
/* Json which contains the information that will be sent to the server */
  changePwdDTO : ChangePwdDTO = {          
    oldpassword : '',
    newpassword :''
  }  

  /* Message that will display the corresponding field in case of error */
  oldPwdErrorMessage : string ="";
  newPwdErrorMessage : string ="";
  confirmNewPwdErrorMessage : string ="";

  /* Will be true if the corresponding field contain an error, else false */
  oldPwdError : boolean = false;
  newPwdError : boolean = false;
  confirmNewPwdError : boolean = false;


  navigateToAccount() : void{
    this.go_account.emit();
  }

  /* Checks if the different fields are empty */
  oldPwd_empty (): boolean{
    if(this.oldPwd==="") {this.oldPwdErrorMessage = "Field Empty";return true; }
    else{return false;}
  }
  newPwd_empty (): boolean{
    if(this.newPwd==="") {this.newPwdErrorMessage = "Field Empty";return true; }
    else{return false;}
  }
  confirmNewPwd_empty (): boolean{
    if(this.confirmNewPwd==="") {this.confirmNewPwdErrorMessage = "Field Empty";return true; }
    else{return false;}
  }

  /* Verify that newPwd and confirmNewPwd are identical */
  samePwd():boolean{
    return (this.newPwd===this.confirmNewPwd);
  }

  /* Update the oldPwd field with "Incorrect password", obtained in the post request */
  oldPwd_notcorrect() : void {
    this.oldPwd = ''; 
    this.oldPwdError = true;
    this.oldPwdErrorMessage = "Incorrect password";
  }


  /* Request to the server and response study */
  request_changePwd(data : ChangePwdDTO) : void{
    this.changePwdService.changepwd(data).subscribe(
      /* Classic Response */ 
      (response) => {
        if (response.status === 200) {
          console.log('Successful registration');
          this.navigateToAccount();
        }
      },
      /* Errors */ 
      (error) => {
        if (error.status === 401) 
          if (error.error && error.error.message) 
            if (error.error.message === 'Incorrect old password') 
              this.oldPwd_notcorrect();                  
      }
    );
  }


  /* Main Fonction */
  changePwd() : void{
    if (this.oldPwd_empty()) this.oldPwdError = true; else this.oldPwdError = false;
    if (this.newPwd_empty()) this.newPwdError = true; else this.newPwdError = false;
    if (this.confirmNewPwd_empty()) this.confirmNewPwdError = true; else this.confirmNewPwdError = false;

    let isSamePassword = this.samePwd();

    if(!this.newPwdError && !isSamePassword){ this.newPwdError = true; this.newPwdErrorMessage = "Not the Same Password";}
    if(!this.confirmNewPwdError && !isSamePassword){this.confirmNewPwdError = true; this.confirmNewPwdErrorMessage = "Not the Same Password";}
  
    if(this.oldPwdError)
      this.oldPwd = ''; 
    if(this.newPwdError)
      this.newPwd = ''
    if(this.confirmNewPwdError)
      this.confirmNewPwd = ''; 
    
    if (!this.oldPwdError&&!this.newPwdError&&!this.confirmNewPwdError){
      this.changePwdDTO = {
        oldpassword: this.oldPwd,
        newpassword : this.newPwd
      }          
      console.log(this.changePwdDTO);
      this.request_changePwd(this.changePwdDTO);
    }

  }






}
