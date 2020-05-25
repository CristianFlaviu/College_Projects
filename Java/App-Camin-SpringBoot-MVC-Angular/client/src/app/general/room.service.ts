import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RoomDto } from './RoomDto';

@Injectable({
  providedIn: 'root'
})

export class RoomService {

  constructor(private httpClient:HttpClient) { }

  
 
  public getAllRooms()
  {
    // let username="flaviu";
    // let password="123123";
    // const headers=new HttpHeaders({Authorization:'Basic'}+btoa(username+":"+password));
    // const headers2 = new HttpHeaders( {authorization : 'Basic ' + username + ':' + password});
    
    return this.httpClient.get<RoomDto[]>("http://localhost:7799/app/admin/allRooms");
  }

  public deleteRoom(id)
  {
  
   
    return this.httpClient.delete("http://localhost:7799/app/admin/deleteRoom/"+id);    
  }

  public addRoom(room)
{
  
    return this.httpClient.post("http://localhost:7799/app/admin/addRoom",room);
}

}