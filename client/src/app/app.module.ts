import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { ApiModule } from './api.module';
import { Configuration } from './configuration';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SettingsComponent } from './components/settings/settings.component';
import { LoginComponent } from './components/login/login.component';
import { CreateAccountComponent } from './components/login/create-account/create-account.component';
import { ForgotPasswordComponent } from './components/login/forgot-password/forgot-password.component';
import { ForgotPasswordCodeComponent } from './components/login/forgot-password/forgot-password-code/forgot-password-code.component';
import { ForgotPasswordNewComponent } from './components/login/forgot-password/forgot-password-new/forgot-password-new.component';
import { SettingsAccountComponent } from './components/settings/settings-account/settings-account.component';
import { SettingsNotificationsComponent } from './components/settings/settings-notifications/settings-notifications.component';
import { SettingsAppearanceComponent } from './components/settings/settings-appearance/settings-appearance.component';
import { SettingsLanguageComponent } from './components/settings/settings-language/settings-language.component';
import { SettingsAcknowlodgementsComponent } from './components/settings/settings-acknowlodgements/settings-acknowlodgements.component';
import { ImageComponent } from './components/settings/settings-appearance/image/image.component';
import { FormsModule } from '@angular/forms';
import { SettingsChgpwdComponent } from './components/settings/settings-chgpwd/settings-chgpwd.component';
import { CApopUpErrorsComponent } from './src/app/components/login/create-account/capop-up-errors/capop-up-errors.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateModule } from '@ngx-translate/core';
import { TranslateModuleConfig } from './app.translate.config';
import { TranslateService } from '@ngx-translate/core';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SettingsComponent,
    LoginComponent,
    CreateAccountComponent,
    ForgotPasswordComponent,
    ForgotPasswordCodeComponent,
    ForgotPasswordNewComponent,
    SettingsAccountComponent,
    SettingsNotificationsComponent,
    SettingsAppearanceComponent,
    SettingsLanguageComponent,
    SettingsAcknowlodgementsComponent,
    ImageComponent,
    SettingsChgpwdComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    // To avoid CORS issues and limitations, we use an Angular reverse proxy to access the server API
    // (see proxy-config.json: it specifies that all HTTP calls to /serverapi/* URLs should be redirected to the server API at http://localhost:8080/*)
    // Here we configure the generated API client for it to use this base path.
    ApiModule.forRoot(() => new Configuration({ basePath: '/serverapi' })),
    //Translate module configuration
    TranslateModule.forRoot(TranslateModuleConfig),
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
    constructor(private translate: TranslateService) {
      translate.setDefaultLang('en');

      const browserLang = translate.getBrowserLang();
      translate.use(browserLang?.match(/en|fr|es/) ? browserLang : 'en');
    }
 }