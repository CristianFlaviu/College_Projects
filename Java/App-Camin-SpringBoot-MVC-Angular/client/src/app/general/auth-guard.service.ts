import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Router ,CanActivate} from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private auth: AuthService,
              private router:Router,
              private snackBar:MatSnackBar ) { }

  canActivate(): boolean {
    if (!this.auth.isAuthentificated()) {
  
      this.snackBar.open("Not Authentificated","",{duration:2000,
        panelClass:'red-snackbar'});
    

      return false;
    }
    return true;
  }
}
