import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router'
import  {DatatransferService} from '../datatransfer.service';
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
  errorarr=[];
  user:any;
  constructor(private authservice:AuthService,private router:Router,private dataService :DatatransferService) { }

  ngOnInit() {
  }

  authenticateSeller(){
    //@ts-ignore
  if(grecaptcha.getResponse()==""){
   return false; 
  }
    this.authservice.authenticateUser({
      username:this.loginform.value.username,
      password:this.loginform.value.password
    })
    .subscribe((data:any)=>{
      console.log(data)
      if(data.message!=null){
      this.errorarr.push(`${data.message}`);
      }
      if(data.data!=null){
        this.user={
          id:data.data.id,
          username:data.data.username
        }
        this.dataService.setData(this.user);
        
      window.localStorage.setItem("token",data.data.token)
      this.router.navigateByUrl('/home');
      console.log(window.localStorage.getItem("token"))
      }
    })
  }


}
