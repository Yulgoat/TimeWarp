import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front';


  /* 
  Cette zone permet le changement de page, lorsque une des variable homepage, profilpage ou messagingpage est à trou, alors app.component.html 
  va pouvoir afficher le component correspondant.

  Les 3 fonctions goHomePage(), goProfilPage() et goMessagingPage() permettent le changement de page en changeant les variables, elles sont appellés par les
  enfants de app.components.html (donc par <app-home-page> , <app-progil-page> et <app-messaging-page>
  */

  home : boolean = true;
  settings : boolean = false;
  login : boolean = false;


  goLogin() : void{
    this.home  = false;
    this.settings  = false;
    this.login = true;
  }

  goSettings() : void{    
    this.home  = false;
    this.settings  = true;
    this.login = false;
  }

  goHome() : void{    
    this.home  = true;
    this.settings  = false;
    this.login = false;
  }

  /* --------------------------------------------------------------------------------------------------------------------------*/



}
