import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDto } from './userDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient)
   {

    
  }

  public register(userDto)
  {
   return this.http.post("http://localhost:7799/app/user/register",userDto);
  }

  public getAllUsers()
  {
    return this.http.get("http://localhost:7799/app/admin/allUsers");
  }

  public login(user)
  {
    return this.http.post("http://localhost:7799/app/user/login",user);
  }

  public getUSerById(id)
  {
    return this.http.get("http://localhost:7799/app/admin/viewUser/"+id);
  }

  public genereteResetPassToken(email)
  {
    let user=new UserDto;
    user.email=email;
    return this.http.post("http://localhost:7799/app/user/resetPassword",user);
  }
  
  public setNewPassword(user)
  {
    return this.http.post("http://localhost:7799/app/user/setNewPassword",user);
  }

  public getByRoomNumbeR(number)
  {
    return this.http.get("http://localhost:7799/app/admin/allUsersRoom/"+number);
  }
}
