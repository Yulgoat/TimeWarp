import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-messaging-page',
  templateUrl: './messaging-page.component.html',
  styleUrls: ['./messaging-page.component.css']
})


export class MessagingPageComponent {

  /*
    Cette zone permet de réaliser le changement de page, lors qu'une des fonctions est appellé (après un click sur un bouton), elle va permettre l'envoie
    d'une message au père (<app.component>) qui va activé la bonne fonction pour changer la page.
  */
  @Output() goHomePage = new EventEmitter<void>();
  @Output() goProfilPage = new EventEmitter<void>();


  mesToHome() : void{
    this.goHomePage.emit();
  }

  mesToProfil() : void{    
    this.goProfilPage.emit();
  }

  /* ----------------------------------------------------------------------------------------------------------------------------------------- */

  
}
