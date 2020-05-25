import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {



  constructor(private http:HttpClient) { }

  public getAllAnnouncements()
  {
    return this.http.get("http://localhost:7799/app/user/allAnnoucement");
  }

  public getAnnouncement(id)
  {
    return this.http.get("http://localhost:7799/app/user/announcement/"+id);
  }
}
