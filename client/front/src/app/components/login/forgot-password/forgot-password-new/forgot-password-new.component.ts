import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password-new',
  templateUrl: './forgot-password-new.component.html',
  styleUrls: ['./forgot-password-new.component.css']
})
export class ForgotPasswordNewComponent {

  constructor(private router:Router){}

  navigateToLogin() : void{
    this.router.navigate(['/login']);
  }

}
