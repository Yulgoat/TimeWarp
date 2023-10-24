import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-ca-popup-errors',
  templateUrl: './ca-popup-errors.component.html',
  styleUrls: ['./ca-popup-errors.component.css']
})
export class CaPopupErrorsComponent {
  @Output() CApopUpError = new EventEmitter<void>();

  @Input() message: string ="";

  hide_popUpError() {
   this.CApopUpError.emit();
 }


}
