import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  /*
    Cette zone permet de réaliser le changement de page, lors qu'une des fonctions est appellé (après un click sur un bouton), elle va permettre l'envoie
    d'une message au père (<app.component>) qui va activé la bonne fonction pour changer la page.
  */
  @Output() goSettings = new EventEmitter<void>();


  homeToSettings() : void{
    this.goSettings.emit();
  }

  /* ----------------------------------------------------------------------------------------------------------------------------------------- */

  
}
