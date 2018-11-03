import { Component, OnInit } from '@angular/core';
import  {DatatransferService} from '../datatransfer.service';
import {ProductService} from '../product.service'
import { productdto } from '../models/productdto';
import {AuthService} from '../auth.service'
import { sellerdto } from '../models/sellerdto';
import { FormGroup, FormControl } from '@angular/forms';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
user:any;
products:productdto;
isLoaded:boolean;
filter:String[];
query:string;
sortform=new FormGroup({
  sortBy:new FormControl(''),
  new:new FormControl(''),
  approved:new FormControl(''),
  rejected:new FormControl(''),
  review:new FormControl(''),
});

searchproducts=new FormGroup({
  search:new FormControl(''),
  searchtext:new FormControl('')
});

  constructor(private dataService :DatatransferService,private productService:ProductService,private authService:AuthService) { 

    this.isLoaded=false;

    this.products={
      status:null,
      data:null,
      message:null,

    }

    this.filter = [];
    
    //console.log(this.user.username)
  }

  ngOnInit() {

    this.authService.currentUser()
    .subscribe((data:sellerdto)=>{
      console.log(data);
      this.user=data.data;

      this.productService.getProductList(this.user.id)
    .subscribe((data:productdto)=>{
      this.products=data;
      console.log(data);
      setTimeout(()=>{
        this.isLoaded=true;
      },1000)
      
    })
    },err => {
      console.log(err)
    })

   
    

    
   
  }
   sortAndFilter(){
     console.log(this.sortform.value.sortBy)
    this.query="?"
    let count:number;
    if(this.sortform.value.sortBy!=""){
      this.query+=`sortBy=${this.sortform.value.sortBy}`
      count=1;
    }
    else{
      count=0;
    }
     if(this.sortform.value.new){
       this.filter = [...this.filter, "NEW"];
       if(count==0){
         this.query+=`status=NEW&`
       }
       else{
        this.query+=`&status=NEW&`
        count=0;


       }
      //this.filter.push("NEW")
     }
     if(this.sortform.value.approved){
      this.filter = [...this.filter, "APPROVED"];
      if(count==0){
        this.query+=`status=APPROVED&`
      }
      else{
       this.query+=`&status=APPROVED&`
       count=0;

      }
      //this.filter.push("APPROVED")
     }
     if(this.sortform.value.rejected){
      this.filter = [...this.filter, "REJECTED"];
      //this.filter.push("REJECTED")
      if(count==0){
        this.query+=`status=REJECTED&`
      }
      else{
       this.query+=`&status=REJECTED&`
       count=0;

      }
     }
     if(this.sortform.value.review){
      this.filter = [...this.filter, "REVIEW"];

      //this.filter.push("REVIEW")
      if(count==0){
        this.query+=`status=REVIEW&`
      }
      else{
       this.query+=`&status=REVIEW&`
       count=0;

      }
     }
     console.log(this.filter);
     this.query = this.query.substring(0, this.query.length - 1); // "12345.0"

     console.log(this.query);
    this.filter=[];
     this.productService.sortAndFilter(this.query)
     .subscribe((data:any)=>{
      console.log(data);
      this.products=data
     })
    
  }
 
  search(){
    let search=this.searchproducts.value.search
    let text=this.searchproducts.value.searchtext
  console.log(search)
  console.log(text)
    if(search==="sellerid"){
      console.log("dsfsdfsfd")

    this.productService.searchSellerID(search,text)
    .subscribe((data:any)=>{
      console.log(data)
      this.products=data

    })
  
} 
else{
  console.log("sds")
  let query=`?${search}=${text}`
  this.productService.search(query)
  .subscribe((data:any)=>{
    console.log(data)
    this.products=data

  }) 
}
}

}
