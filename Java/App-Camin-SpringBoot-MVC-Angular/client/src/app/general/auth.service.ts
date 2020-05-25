import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient } from '@angular/common/http';
import { UserDto } from './userDto';

var jwtDecode = require('jwt-decode');

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(public jwtHelper: JwtHelperService,private http:HttpClient) { }

  setSesion(token) {
  
    localStorage.setItem("token", token);
  

  }

  decedode(token)
  {
    return jwtDecode(token);
  }

  public decodeToken()
  {
    return jwtDecode(localStorage.getItem("token"));
    //return this.http.get<UserDto>("http://localhost:7799/app/user/token");
  }

  public getRole()
  {
    if(this.isAuthentificated())
 
      return jwtDecode(localStorage.getItem('token')).role;

    return null;
  }

  public getRoomNumber()
  {
    if(this.isAuthentificated())
 
    return jwtDecode(localStorage.getItem('token')).roomNumber;

  return null;
  }

 public isAuthentificated(): boolean {

    const token = localStorage.getItem('token');
 
    if (token)
    {
      // console.log("IsEXPIRED "+this.jwtHelper.isTokenExpired(token))
     
      return !this.jwtHelper.isTokenExpired(token);
    }

     return false;
  }

  public getUserId()
  {
    if(this.isAuthentificated())
    {
    return jwtDecode(localStorage.getItem("token")).sub;
    }
  }
  public logOut()
  {
    localStorage.removeItem("token");
  }


 
}
