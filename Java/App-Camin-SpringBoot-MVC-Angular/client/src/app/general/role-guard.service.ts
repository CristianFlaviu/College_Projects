import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService  implements CanActivate{

  constructor(public auth: AuthService, public router: Router,public snackBar:MatSnackBar){}

  canActivate(route: ActivatedRouteSnapshot): boolean {

    const expectedRole = route.data.expectedRole;
    const token = localStorage.getItem('token');

     const tokenPayload = this.auth.decodeToken();

     console.log("token role "+tokenPayload.role);
     console.log("expected role "+expectedRole);
    //  tokenPayload.role != expectedRole
     
    if ( !expectedRole.includes(tokenPayload.role)) 
    {
      this.snackBar.open("YOU DONT HAVE PERMISION ","",{duration:2000,
        panelClass:'red-snackbar'});
    
     
      return false;
    }
    return true;
  }

}
