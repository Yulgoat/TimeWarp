import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateAccountComponent } from './components/login/create-account/create-account.component';
import { ForgotPasswordComponent } from './components/login/forgot-password/forgot-password.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { SettingsComponent } from './components/settings/settings.component';
import { ForgotPasswordCodeComponent } from './components/login/forgot-password/forgot-password-code/forgot-password-code.component';
import { ForgotPasswordNewComponent } from './components/login/forgot-password/forgot-password-new/forgot-password-new.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirige vers /login par défaut
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent},
  { path: 'settings', component: SettingsComponent},
  { path: 'create-account', component: CreateAccountComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent},
  { path: 'forgot-password-code', component: ForgotPasswordCodeComponent },
  { path: 'forgot-password-new', component: ForgotPasswordNewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
