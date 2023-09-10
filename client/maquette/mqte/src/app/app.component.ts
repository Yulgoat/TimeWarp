import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mqte';


  /* 
  Cette zone permet le changement de page, lorsque une des variable homepage, profilpage ou messagingpage est à trou, alors app.component.html 
  va pouvoir afficher le component correspondant.

  Les 3 fonctions goHomePage(), goProfilPage() et goMessagingPage() permettent le changement de page en changeant les variables, elles sont appellés par les
  enfants de app.components.html (donc par <app-home-page> , <app-progil-page> et <app-messaging-page>
  */

  homepage : boolean = true;
  profilpage : boolean = false;
  messagingpage : boolean = false;


  goHomePage() : void{
    this.homepage  = true;
    this.profilpage  = false;
    this.messagingpage = false;
  }

  goProfilPage() : void{    
    this.homepage  = false;
    this.profilpage  = true;
    this.messagingpage = false;
  }

  goMessagingPage() : void{    
    this.homepage  = false;
    this.profilpage  = false;
    this.messagingpage = true;
  }

  /* --------------------------------------------------------------------------------------------------------------------------*/





}
