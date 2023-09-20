import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router:Router){}

  loginToHome() : void{
    this.router.navigate(['/home']);
  }

  navigateToForgotPassword() {
    this.router.navigate(['/forgot-password']);
  }

  navigateToCreateAccount(){
    this.router.navigate(['/create-account'])
  }

  /* ----------------------------------------------------------------------------------------------------------------------------------------- */


}
