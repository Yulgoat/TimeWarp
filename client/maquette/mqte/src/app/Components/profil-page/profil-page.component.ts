import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-profil-page',
  templateUrl: './profil-page.component.html',
  styleUrls: ['./profil-page.component.css']
})
export class ProfilPageComponent {

  /*
    Cette zone permet de réaliser le changement de page, lors qu'une des fonctions est appellé (après un click sur un bouton), elle va permettre l'envoie
    d'une message au père (<app.component>) qui va activé la bonne fonction pour changer la page.
  */
  @Output() goHomePage = new EventEmitter<void>();
  @Output() goMessagingPage = new EventEmitter<void>();


  profToHome() : void{
    this.goHomePage.emit();
  }

  profToMes() : void{    
    this.goMessagingPage.emit();
  }

  /* ----------------------------------------------------------------------------------------------------------------------------------------- */




}
