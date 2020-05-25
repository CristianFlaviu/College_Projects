import { Component, OnInit, ViewChild } from '@angular/core';
import { UserDto } from '../userDto';
import { UserService } from '../user.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.css']
})
export class RoomDetailsComponent implements OnInit {


  displayedColumns: string[] = ['firstName',"lastName","username","email"];

  listUsers:UserDto[]=[];
  response;
  dataSource;
  roomNumber:String;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
   @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  constructor(private userService:UserService,
              private route: ActivatedRoute,) { }

  ngOnInit(): void {

    this.roomNumber=this.route.snapshot.paramMap.get('id');
    let resp=this.userService.getByRoomNumbeR(this.roomNumber);
    

    resp.subscribe(data=>{
    this.response=data;

    this.listUsers=this.response.data;
    this.dataSource=new MatTableDataSource(this.listUsers);
    this.dataSource.sort=this.sort;
    this.dataSource.paginator=this.paginator;
   
    },
    err=>{
          console.log(err);
       
    })



  }

}
