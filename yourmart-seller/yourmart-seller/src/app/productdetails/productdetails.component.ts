import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import {ProductService} from '../product.service'
import { FormControl,FormGroup } from '@angular/forms';
import {AuthService} from '../auth.service';
import { sellerdto } from '../models/sellerdto';



@Component({
  selector: 'app-productdetails',
  templateUrl: './productdetails.component.html',
  styleUrls: ['./productdetails.component.css']
})
export class ProductdetailsComponent implements OnInit {
  productid:any;
   user:any;
  product:any;
  show:boolean;
  newproduct:any;
  categories=new Array<string>()
  productDetails = new FormGroup({
    productcode: new FormControl(''),
    productname: new FormControl(''),
    shortdiscription: new FormControl(''),
    dimensions: new FormControl(''),
    longdiscription: new FormControl(''),
    usageinstructins: new FormControl(''),
    mrp: new FormControl(''),
    ssp: new FormControl(''),
    ymp: new FormControl(''),
    categorylist:new FormControl('')
    
        
  });

  constructor(private route: ActivatedRoute,private authService:AuthService, private routes: Router,private productService:ProductService) {

    this.route.params.subscribe(params => {
      this.productid = params.id;
      if(this.productid==null){
        this.newproduct=true;
      }
    });

   }

  ngOnInit() {

    this.authService.currentUser()
    .subscribe((data:sellerdto)=>{
      console.log(data);
      this.user=data.data;
    })

    this.productService.getproduct(this.productid).subscribe((data:any)=>{
      if(data.data!=null){
      this.product=data.data;
      
      console.log(this.product)
      this.productDetails.controls['productcode'].setValue(this.product.sellerproductcode);
      this.productDetails.controls['productname'].setValue(this.product.productname);
      this.productDetails.controls['shortdiscription'].setValue(this.product.shortdiscription);
      this.productDetails.controls['dimensions'].setValue(this.product.dimensions);
      this.productDetails.controls['longdiscription'].setValue(this.product.longdiscription);
      this.productDetails.controls['mrp'].setValue(this.product.mrp);
      this.productDetails.controls['ssp'].setValue(this.product.ssp);
      this.productDetails.controls['ymp'].setValue(this.product.ymp);
      this.productDetails.controls['categorylist'].setValue(this.product.categories);
     
     this.productDetails.controls['usageinstructins'].setValue(this.product.usageinstructins);  
    } 
    setTimeout(()=>{     
       this.show=true;
    },
    1000
    )
    });
  }

  

  saveProduct(){
    if(this.newproduct){
      this.addProduct();
    }
    else{
      this.updateProduct();
    }
  }

  addProduct(){
    this.productService.addproduct({
      sellerproductcode:this.productDetails.value.productcode,
      productname:this.productDetails.value.productname,
      shortdiscription:this.productDetails.value.shortdiscription,
      longdiscription:this.productDetails.value.longdiscription,
      dimensions:this.productDetails.value.dimensions,
      mrp:this.productDetails.value.mrp,
      ssp:this.productDetails.value.ssp,
      ymp:this.productDetails.value.ymp,
      primaryimage:this.product.primaryimage,
      usageinstructins:this.productDetails.value.usageinstructins,
      sellerId:window.localStorage.getItem('sellerid'),
      
    })
  }

  updateProduct(){
  
    this.productService.updateproduct({
      id:this.productid,
      
      sellerproductcode:this.productDetails.value.productcode,
      productname:this.productDetails.value.productname,
      shortdiscription:this.productDetails.value.shortdiscription,
      longdiscription:this.productDetails.value.longdiscription,
      dimensions:this.productDetails.value.dimensions,
      mrp:this.productDetails.value.mrp,
      ssp:this.productDetails.value.ssp,
      ymp:this.productDetails.value.ymp,
      primaryimage:this.product.primaryimage,
      usageinstructins:this.productDetails.value.usageinstructins,
    
      sellerId:this.product.sellerId,
      categories:this.categories
      

    }).subscribe((data:any)=>{
      console.log(data)
    })
    console.log("productcode:"+this.productDetails.value.productcode)
  }
}





