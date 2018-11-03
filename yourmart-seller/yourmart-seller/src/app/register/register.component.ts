import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  errorarr=[];
  registerform=new FormGroup({
    username:new FormControl(''),
    password:new FormControl(''),
    confirmpassword:new FormControl(''),
    companyname:new FormControl(''),
    ownername:new FormControl(''),
    address:new FormControl(''),
    email:new FormControl(''),
    telephone:new FormControl(''),
    gstnumber:new FormControl('')
  });


  constructor(private authservice:AuthService,private router:Router) { }

  ngOnInit() {
  }

  registerSeller(){
    this.authservice.registerSeller({
      username:this.registerform.value.username,

      password:this.registerform.value.password,

      confirmpassword:this.registerform.value.confirmpassword,

      companyname:this.registerform.value.companyname,

      ownername:this.registerform.value.ownername,

      address:this.registerform.value.address,

      email:this.registerform.value.email,

      telephone:this.registerform.value.telephone,

      gstnumber:this.registerform.value.gstnumber



      

    })
    .subscribe((data:any)=>{
      console.log(data)
      if(data.message!=null){
      this.errorarr.push(`${data.message}`);
      }
      if(data.data!=null){
      this.router.navigateByUrl('');
      }
    })
  }

}
