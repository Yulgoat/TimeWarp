import { Component, OnInit } from '@angular/core';
import { UserSettingsService } from 'src/app/services/user-settings.service';

@Component({
  selector: 'app-settings-appearance',
  templateUrl: './settings-appearance.component.html',
  styleUrls: ['./settings-appearance.component.css']
})
export class SettingsAppearanceComponent{

  private themeId = 0;

  // Arrays to hold image data for the 1960s and 1970s with selection state
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

  // Variables to keep track of the selected image indices for each era
  selectedIndex60s: number | null = 0;
  selectedIndex70s: number | null = null;

  constructor(private userSettingsService: UserSettingsService){
    this.themeId = this.userSettingsService.themeId;
    if(this.themeId<this.images60s.length) {
      this.selectedIndex60s = this.themeId;
      this.selectedIndex70s = null;
    }
    else if(this.themeId<this.images60s.length+this.images70s.length) {
      this.selectedIndex60s = null;
      this.selectedIndex70s = this.themeId-this.images60s.length;
    }
  }

  // Function to handle image selection
  selectImage(index: number, imageArray: any[], era: string) {

    let themeId = 0;
    // Depending on the era, select or deselect images and update the selected index
    if (era === '60s') {
      this.selectedIndex70s = null; // Deselect the image in the 70s era
      this.selectedIndex60s = index; // Select the image in the 60s era
      themeId=index;
    } else if (era === '70s') {
      this.selectedIndex60s = null; // Deselect the image in the 60s era
      this.selectedIndex70s = index; // Select the image in the 70s era
      themeId=index+this.images60s.length
    }

    this.userSettingsService.updateTheme(themeId);
  }

}
