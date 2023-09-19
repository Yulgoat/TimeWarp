import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-new-conv-popup',
  templateUrl: './new-conv-popup.component.html',
  styleUrls: ['./new-conv-popup.component.css']
})
export class NewConvPopupComponent {
   @Output() new_conv_popup = new EventEmitter<void>();

  hide_new_conv_popup() {
    this.new_conv_popup.emit();
  }

}
