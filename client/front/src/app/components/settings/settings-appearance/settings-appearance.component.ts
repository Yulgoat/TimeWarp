import { Component } from '@angular/core';

@Component({
  selector: 'app-settings-appearance',
  templateUrl: './settings-appearance.component.html',
  styleUrls: ['./settings-appearance.component.css']
})
export class SettingsAppearanceComponent {

  images60s: { src: string; isSelected: boolean }[] = [
    { src: '/assets/images/12498931_4965007.jpeg', isSelected: false },
    { src: '/assets/images/12277065_4945415.jpeg', isSelected: false },
    { src: '/assets/images/12498935_5012660.jpeg', isSelected: false }
  ];

  images70s: { src: string; isSelected: boolean }[] = [
    { src: '/assets/images/vecteezy_abstract-spiral-starburst-or-sunburst-background-colorful_15261673.jpg', isSelected: false },
    { src: '/assets/images/vecteezy_retro-background-groovy-background_6797719.jpg', isSelected: false },
    { src: '/assets/images/35237771_8287145.jpeg', isSelected: false }
  ];

  selectedIndex60s: number | null = null;
  selectedIndex70s: number | null = null;

  selectImage(index: number, imageArray: any[], era: string) {

    if (era === '60s') {
      this.selectedIndex70s = null; // Désélectionner l'image dans les années 70
      this.selectedIndex60s = index; // Sélectionner l'image dans les années 60
    } else if (era === '70s') {
      this.selectedIndex60s = null; // Désélectionner l'image dans les années 60
      this.selectedIndex70s = index; // Sélectionner l'image dans les années 70
    }

    imageArray.forEach((image, i) => {
      image.isSelected = i === index;
    });
  }

}
