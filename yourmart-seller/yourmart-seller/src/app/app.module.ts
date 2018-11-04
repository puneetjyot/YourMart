import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule,FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProductdetailsComponent } from './productdetails/productdetails.component';
import { SingleproductComponent } from './singleproduct/singleproduct.component'; 
import {SlideshowModule} from 'ng-simple-slideshow';

const routes: Routes = [

    {
    path: '',
    component: LoginComponent
    },
    {
      path:'register',
      component:RegisterComponent
    },
    {
      path:'home',
      component:HomeComponent
    },
    {
      path:'productedit/:id',
      component:ProductdetailsComponent
    },
    {
      path:'newproduct',
      component:ProductdetailsComponent
    },
    {
      path:'product/:id',
      component:SingleproductComponent

    }

  ]; 
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProductdetailsComponent,
    SingleproductComponent
  ],
  imports: [
    BrowserModule,
    SlideshowModule,
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
