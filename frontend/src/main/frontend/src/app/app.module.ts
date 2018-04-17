import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

import { AppComponent } from './app.component';
import {RegisterComponent} from "./account/register/register.component";
import {AccountService} from "./account/account.service";
import {LoginComponent} from "./account/login/login.component";
import {ProfileComponent} from "./account/success/profile.component";
import {AppRoutingModule} from "./app-routing.module";
import {AuthGuard} from "./account/auth.guard";

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [AccountService,
              AuthGuard,],
  bootstrap: [AppComponent]
})
export class AppModule { }
