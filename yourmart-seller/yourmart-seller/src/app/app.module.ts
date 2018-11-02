import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule,FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'; 
const routes: Routes = [

    {
    path: '',
    component: LoginComponent
    }
  ]; 
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes, {
      onSameUrlNavigation: 'reload'
      }) ,
      ReactiveFormsModule,
      FormsModule,
      HttpClientModule

  ],
  
  exports:[RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
