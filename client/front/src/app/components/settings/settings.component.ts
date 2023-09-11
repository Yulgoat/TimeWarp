import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent {

  /*
    Cette zone permet de réaliser le changement de page, lors qu'une des fonctions est appellé (après un click sur un bouton), elle va permettre l'envoie
    d'une message au père (<app.component>) qui va activé la bonne fonction pour changer la page.
  */
  @Output() goLogin = new EventEmitter<void>();
  @Output() goHome = new EventEmitter<void>();


  settingsToHome() : void{
    this.goHome.emit();
  }

  settingsToLogin() : void{    
    this.goLogin.emit();
  }

  /* ----------------------------------------------------------------------------------------------------------------------------------------- */




}
