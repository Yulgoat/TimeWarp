import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.css']
})
export class ImageComponent {

  @Input() src: string="";
  @Input() isSelected: boolean=false;
  @Output() imageClicked = new EventEmitter<void>();

  onClick() {
    this.imageClicked.emit();
  }

}
