import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {

  /*
    Cette zone permet de réaliser le changement de page, lors qu'une des fonctions est appellé (après un click sur un bouton), elle va permettre l'envoie
    d'une message au père (<app.component>) qui va activé la bonne fonction pour changer la page.
  */
  @Output() goMessagingPage = new EventEmitter<void>();
  @Output() goProfilPage = new EventEmitter<void>();


  homeToMes() : void{
    this.goMessagingPage.emit();
  }

  homeToProfil() : void{    
    this.goProfilPage.emit();
  }

  /* ----------------------------------------------------------------------------------------------------------------------------------------- */


}
