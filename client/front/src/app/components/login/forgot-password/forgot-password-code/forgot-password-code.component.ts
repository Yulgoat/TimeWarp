import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password-code',
  templateUrl: './forgot-password-code.component.html',
  styleUrls: ['./forgot-password-code.component.css']
})
export class ForgotPasswordCodeComponent {

  constructor(private router:Router){}

  navigateToNew() : void{
    this.router.navigate(['/forgot-password-new']);
  }

}
