import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'; 
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  BASEURL:string;
constructor(private http:HttpClient){
this.BASEURL='http://localhost:8090/';
}

getProductList(id){

  return this.http.get(`${this.BASEURL}products/${id}`);


}
sortAndFilter(query){
  
  console.log(query)

  return this.http.get(`${this.BASEURL}product${query}`)
}

searchSellerID(search,text){
 
    return this.http.get(`${this.BASEURL}products/${text}`)
  
}
search(query){
  return this.http.get(`${this.BASEURL}list/product/search${query}`)
}
addproduct(product){
return this.http.post(`${this.BASEURL}product`,product)
}
updateproduct(product){

  return this.http.put(`${this.BASEURL}product`,product,
  {
    headers: {
      'Content-Type' : 'application/json; charset=utf-8',
      'Accept'       : 'application/json',
      'token': `${window.localStorage.getItem("token")}`
    }
  }
  );

}
getproduct(productid){
  return this.http.get(`${this.BASEURL}singleproduct/${productid}`);

}
}
