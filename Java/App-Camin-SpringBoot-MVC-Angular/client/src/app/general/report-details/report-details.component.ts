import { Component, OnInit } from '@angular/core';
import { ReportDto } from '../reportDto';
import { ActivatedRoute } from '@angular/router';
import { ReportService } from '../report.service';

@Component({
  selector: 'app-report-details',
  templateUrl: './report-details.component.html',
  styleUrls: ['./report-details.component.css']
})
export class ReportDetailsComponent implements OnInit {

  report = new ReportDto();
  response;

  constructor(private route: ActivatedRoute,
    private reportService: ReportService) { }

  ngOnInit(): void {
    this.getRepport();
  }

  getRepport(): void {
    const id = this.route.snapshot.paramMap.get('id');

    this.reportService.getReport(id).subscribe(
      data => {
        this.response = data;
        this.report = this.response.data;

      },
      err => {


      }
    )



  }
}
