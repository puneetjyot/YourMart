import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth.service';
import { sellerdto } from '../models/sellerdto';
import {ProductService} from '../product.service';
import { ActivatedRoute, Router } from "@angular/router";


@Component({
  selector: 'app-singleproduct',
  templateUrl: './singleproduct.component.html',
  styleUrls: ['./singleproduct.component.css']
})
export class SingleproductComponent implements OnInit {

  loaded:boolean=false;
  product:any;
  newproduct:any;
  productid:any;

  constructor( private routes: Router,private route: ActivatedRoute,private authService:AuthService,private productService:ProductService) { 
    this.loaded=false;
    this.route.params.subscribe(params => {
      this.productid = params.id;
      if(this.productid==null){
        this.newproduct=true;
      }
    });

  }
isLoaded:boolean=false;
  user:any;
  ngOnInit() {

    this.productService.getproduct(this.productid)
    .subscribe((data:any)=>{
      if(data.data!=null){
      this.product=data.data;
      console.log(this.product)
      this.isLoaded=true;
      this.authService.currentUser()
      .subscribe((data:sellerdto)=>{
        console.log(data);
        this.user=data.data;
     
      })
      }
    });

   
  }
  


}
