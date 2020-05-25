import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { AppointmentDto } from '../appointmentDto';
import { AppComponent } from 'src/app/app.component';
import { WaterMachineService } from '../water-machine.service';
import { MatTableDataSource } from '@angular/material/table';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { format } from 'path';

@Component({
  selector: 'app-water-machine-app',
  templateUrl: './water-machine-app.component.html',
  styleUrls: ['./water-machine-app.component.css']
})
export class WaterMachineAppComponent implements OnInit {

  constructor(public wmService: WaterMachineService,
    public autService: AuthService,
    private userService: UserService,
    private snackBar: MatSnackBar) { }

  listDays = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday","Sunday"];
  listHours = ["8", "10", "12", "14", "16", "18", "20"]

  form: FormGroup = new FormGroup({
    day: new FormControl(''),
    hour: new FormControl(''),

  });



  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  displayedColumns: string[] = ["position", 'roomNumber', "startHour", "day", "Action"];
  listWmApp: AppointmentDto[];
  roomNumber;
  dataSource;
  response;


  ngOnInit(): void {

    let resp = this.wmService.getAll();

    resp.subscribe(data => {

      this.response = data
      this.listWmApp = this.response.data;
      this.dataSource = new MatTableDataSource(this.listWmApp);

      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;

      this.roomNumber = this.autService.getRoomNumber();

    },
      error => {
        console.log(error);
      }
    )

  }


  get day() { return this.form.value.day }
  get hour() { return this.form.value.hour }

  applyFilter(filterValue: string) {

    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.dataSource.paginator = this.paginator;

  }
  checkField() {
    if (this.day.length > 2 && this.hour.length > 1)
      return true;
    return false
  }

  submit() {
    console.log(this.day);
    console.log(this.day.length)
    let app: AppointmentDto = new AppointmentDto;
    app.day = this.form.value.day;
    app.startHour = this.form.value.hour;
    app.roomNumber = this.roomNumber;

    this.wmService.addApp(app).subscribe(data => {
      this.snackBar.open("New Appointment created", "", {
        duration: 2000,
        panelClass: 'blue-snackbar'
      });

      this.wmService.getAll().subscribe(data => {
        this.response = data;
        this.listWmApp = this.response.data;
        this.dataSource = new MatTableDataSource(this.listWmApp);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;

      })
    },
      err => {
        this.snackBar.open(err.error.message, "", {
          duration: 2000,
          panelClass: 'red-snackbar'
        });
      })


  }


  deleteApp(id) {
    this.wmService.deleteApp(id).subscribe(data => {

      this.response = data;
      this.listWmApp = this.response.data;

      this.dataSource = new MatTableDataSource(this.listWmApp);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;

      console.log(this.roomNumber);
      console.log(this.listWmApp);



      this.snackBar.open("App Deleted", "", {
        duration: 2000,
        panelClass: 'red-snackbar'
      });

    },
      err => {
        this.snackBar.open("invalid Delete", "", {
          duration: 2000,
          panelClass: 'red-snackbar'
        });
        console.log(err)
      })
  }

}
