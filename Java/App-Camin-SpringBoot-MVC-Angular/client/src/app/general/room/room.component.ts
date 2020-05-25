import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatSortModule, MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { RoomDto } from '../RoomDto';
import { RoomService } from '../room.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})


export class RoomComponent implements OnInit {

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  displayedColumns: string[] = ['No', 'roomNumber', "NoStudents", "Action"];

  listRooms: RoomDto[];

  dataSource;
  response;

  formGroup: FormGroup;



  constructor(private service: RoomService,
    private _snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private snackBar:MatSnackBar) { }


    snackbar_message(color: String, message: string) {
      this.snackBar.open(message, "", {
        duration: 2000,
        panelClass: color + "-snackbar"
      });
    }

  ngOnInit(): void {

    this.updateTable();
    this.formGroup=this.formBuilder.group({
      
      roomNumber:['',[Validators.required]]
    })

  }

  get roomNumber(){return this.formGroup.get("roomNumber"); }

  createRoom()
  {
    let room=new RoomDto();
    room.roomNumber=this.formGroup.value.roomNumber;
    this.service.addRoom(room).subscribe(
      data=>{
        this.updateTable();
      
      },
      err=>{
        this.snackbar_message("red",err.error.message)
      }

    );
    
  }
  updateTable() {

    let resp = this.service.getAllRooms();
    resp.subscribe(data => {

      this.response = data
      this.listRooms = this.response.data;
      this.dataSource = new MatTableDataSource(this.listRooms);

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

  delete(id) {

    let resp = this.service.deleteRoom(id);

    resp.subscribe(data => {


      this.response = data;
      this.listRooms = this.response.data;
      this.dataSource = new MatTableDataSource(this.listRooms);
      this._snackBar.open(this.response.message, "", { duration: 2500, panelClass: "green-snackbar" });

    },
      error => {
        console.log(error);

        this._snackBar.open(error.error.message, "", {
          duration: 2500,
          panelClass: ['red-snackbar']
        });

      }
    )


  }

}
