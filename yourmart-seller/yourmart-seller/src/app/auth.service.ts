import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  BASEURL:string;
constructor(private http:HttpClient){
this.BASEURL='http://localhost:8090/';
}

authenticateUser(user:any){
  return this.http.post(`${this.BASEURL}login/seller`,user);
}
registerSeller(seller:any){
  return this.http.post(`${this.BASEURL}register/seller`,seller);
}
currentUser(){
  console.log("dsasd")
  return this.http.get(`${this.BASEURL}currentseller`,
  {
    headers: {
      'Content-Type' : 'application/json; charset=utf-8',
      'Accept'       : 'application/json',
      'token': `${window.localStorage.getItem("token")}`
    }
  
}
)
}
}
