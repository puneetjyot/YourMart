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

}
