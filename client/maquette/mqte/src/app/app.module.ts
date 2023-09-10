import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { ProfilPageComponent } from './Components/profil-page/profil-page.component';
import { MessagingPageComponent } from './Components/messaging-page/messaging-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    ProfilPageComponent,
    MessagingPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
