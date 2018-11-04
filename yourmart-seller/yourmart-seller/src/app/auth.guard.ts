import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot ,Router} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(private route:Router){

  }
  
  
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.auth();
  }
  auth(){
    if(window.localStorage.getItem("token")!=undefined){
      return true;
    }
    else{
      this.route.navigate(['/'])
      return false;
    }
  }
}
