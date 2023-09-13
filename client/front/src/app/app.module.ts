import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SettingsComponent } from './components/settings/settings.component';
import { LoginComponent } from './components/login/login.component';
import { CreateAccountComponent } from './components/login/create-account/create-account.component';
import { ForgotPasswordComponent } from './components/login/forgot-password/forgot-password.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SettingsComponent,
    LoginComponent,
    CreateAccountComponent,
    ForgotPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
