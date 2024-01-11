import { Component, EventEmitter, Output } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { UserSettingsService } from 'src/app/services/user-settings.service';

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

  constructor(private userSettingsService: UserSettingsService, private translate: TranslateService){}
  
  @Output() go_account = new EventEmitter<void>();


  oldPwd  = "";
  newPwd  = "";
  confirmNewPwd  ="";
  
/* Json which contains the information that will be sent to the server */
  changePwdDTO : ChangePwdDTO = {          
    oldpassword : '',
    newpassword :''
  }  

  /* Message that will display the corresponding field in case of error */
  oldPwdErrorMessage  ="";
  newPwdErrorMessage  ="";
  confirmNewPwdErrorMessage  ="";

  /* Will be true if the corresponding field contain an error, else false */
  oldPwdError  = false;
  newPwdError  = false;
  confirmNewPwdError  = false;


  navigateToAccount() : void{
    this.go_account.emit();
  }

  /* Checks if the different fields are empty */
  oldPwd_empty (): boolean{
    if(this.oldPwd==="") {this.oldPwdErrorMessage = this.translate.instant('FieldEmpty');return true; }
    else{return false;}
  }
  newPwd_empty (): boolean{
    if(this.newPwd==="") {this.newPwdErrorMessage = this.translate.instant('FieldEmpty');return true; }
    else{return false;}
  }
  confirmNewPwd_empty (): boolean{
    if(this.confirmNewPwd==="") {this.confirmNewPwdErrorMessage = this.translate.instant('FieldEmpty');return true; }
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
    this.oldPwdErrorMessage = this.translate.instant('IncorrectPassword');
  }


  /* Request to the server and response study */
  request_changePwd(data : ChangePwdDTO) : void{
    this.userSettingsService.changepwd(data).subscribe(
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

    const isSamePassword = this.samePwd();

    if(!this.newPwdError && !isSamePassword){ this.newPwdError = true; this.newPwdErrorMessage = this.translate.instant('NotTheSamePassword');}
    if(!this.confirmNewPwdError && !isSamePassword){this.confirmNewPwdError = true; this.confirmNewPwdErrorMessage = this.translate.instant('NotTheSamePassword');}
  
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
