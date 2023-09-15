import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SettingsComponent } from './components/settings/settings.component';
import { LoginComponent } from './components/login/login.component';
import { CreateAccountComponent } from './components/login/create-account/create-account.component';
import { ForgotPasswordComponent } from './components/login/forgot-password/forgot-password.component';
import { NewConvPopupComponent } from './components/home/new-conv-popup/new-conv-popup.component';
import { ForgotPasswordCodeComponent } from './components/login/forgot-password/forgot-password-code/forgot-password-code.component';
import { ForgotPasswordNewComponent } from './components/login/forgot-password/forgot-password-new/forgot-password-new.component';
import { SettingsAccountComponent } from './components/settings/settings-account/settings-account.component';
import { SettingsNotificationsComponent } from './components/settings/settings-notifications/settings-notifications.component';
import { SettingsAppearanceComponent } from './components/settings/settings-appearance/settings-appearance.component';
import { SettingsLanguageComponent } from './components/settings/settings-language/settings-language.component';
import { SettingsAcknowlodgementsComponent } from './components/settings/settings-acknowlodgements/settings-acknowlodgements.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SettingsComponent,
    LoginComponent,
    CreateAccountComponent,
    ForgotPasswordComponent,
    NewConvPopupComponent,
    ForgotPasswordCodeComponent,
    ForgotPasswordNewComponent,
    SettingsAccountComponent,
    SettingsNotificationsComponent,
    SettingsAppearanceComponent,
    SettingsLanguageComponent,
    SettingsAcknowlodgementsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
