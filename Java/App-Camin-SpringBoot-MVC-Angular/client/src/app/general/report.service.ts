import { Injectable } from '@angular/core';
import { Http2ServerRequest } from 'http2';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http:HttpClient) { }


  getReport(id)
  {
    return this.http.get("http://localhost:7799/app/user/report/"+id);

  }

  createReport(report)
  {
    return this.http.post("http://localhost:7799/app/user/createReport",report);

  }

  getAllReports()
  {
    return this.http.get("http://localhost:7799/app/user/allReports");
  }
  
  

  getReportsByRoom(roomNumber)
  {
    return this.http.get("http://localhost:7799/app/user/reportRoom/"+roomNumber)
  }

}
