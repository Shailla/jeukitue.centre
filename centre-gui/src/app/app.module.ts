import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { UserService } from './user.service';

import { AppComponent } from './app.component';
import { UsersComponent } from './users/users.component';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    UsersComponent
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
