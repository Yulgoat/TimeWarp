import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {

  constructor(private router:Router){}

  navigateToCode() : void{
    this.router.navigate(['/forgot-password-code']);
  }

  navigateToLogin() : void{
    this.router.navigate(['/login']);
  }


}
