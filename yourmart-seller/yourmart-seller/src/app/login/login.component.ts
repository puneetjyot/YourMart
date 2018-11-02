import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginform=new FormGroup({
    username:new FormControl(''),
    password:new FormControl('')
  });

  constructor(private authservice:AuthService,private router:Router) { }

  ngOnInit() {
  }

  authenticateSeller(){
    this.authservice.authenticateUser({
      username:this.loginform.value.username,
      password:this.loginform.value.password
    })
    .subscribe((data:any)=>{
      console.log(data)
    })
  }


}
