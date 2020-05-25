import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ReportService } from '../report.service';
import { ReportDto } from '../reportDto';
import { AuthService } from '../auth.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.css']
})
export class CreateReportComponent implements OnInit {

  formGroup: FormGroup;
  displayedColumns: string[] = ["position",'title','date', "Action"];
  listReports: ReportDto[];

  dataSource;
  response;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  constructor(private reportService: ReportService,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({

      title: ['', [Validators.required, Validators.minLength(5)]],
      description: ['', [Validators.required, Validators.minLength(10)]]
    });

    this.updateTable();

  }

  get title() { return this.formGroup.get("title"); }
  get description() { return this.formGroup.get("description"); }


  submit() {
    let report = new ReportDto();
    report.description = this.formGroup.value.description;
    report.title = this.formGroup.value.title;
    report.roomNumber = this.authService.getRoomNumber();

    this.reportService.createReport(report).subscribe(
      data => {
        this.updateTable();
        this.formGroup.reset();
      }
      ,
      err => {
        console.log(err);

      }
    )
  }

  updateTable()
  {
    let resp = this.reportService.getReportsByRoom(this.authService.getRoomNumber());
    resp.subscribe(data => {

      this.response = data
      this.listReports = this.response.data;
      this.dataSource = new MatTableDataSource(this.listReports);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    },
      error => {
        console.log(error);
      }
    )
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.dataSource.paginator = this.paginator;
  }




}
