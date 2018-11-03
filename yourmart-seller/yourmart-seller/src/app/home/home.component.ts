import { Component, OnInit } from '@angular/core';
import  {DatatransferService} from '../datatransfer.service';
import {ProductService} from '../product.service'
import { productdto } from '../models/productdto';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
user:any;
products:productdto;
  constructor(private dataService :DatatransferService,private productService:ProductService) { 

    this.user=this.dataService.getData();
    //console.log(this.user.username)
  }

  ngOnInit() {

    this.productService.getProductList(this.user.id)
    .subscribe((data:productdto)=>{
      this.products=data;
      console.log(data);

    })

  }

}
