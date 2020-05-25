import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class WaterMachineService {

  constructor(public http:HttpClient) { }


  public deleteApp(id)
  {
    return this.http.delete("http://localhost:7799/app/user/deleteAppointment/"+id);
  }

  public getAll(){
    return this.http.get("http://localhost:7799/app/user/allWMAppointment");
  }

  public addApp(app)
  {
    return this.http.post("http://localhost:7799/app/user/createAppWM",app);
  }
}
