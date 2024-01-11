import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { UserSettingsService } from 'src/app/services/user-settings.service';

@Component({
  selector: 'app-settings-language',
  templateUrl: './settings-language.component.html',
  styleUrls: ['./settings-language.component.css']
})
export class SettingsLanguageComponent {

  selectedLanguage = 'browser';

  constructor(private userSettingsService: UserSettingsService, private translate: TranslateService) {
    this.selectedLanguage = userSettingsService.language;
  }

  changeLanguage(language: string): void {
    this.userSettingsService.updateLanguage(language);
  }
}
