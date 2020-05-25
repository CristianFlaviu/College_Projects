import { Component, OnInit,ViewChild } from '@angular/core';
import { UserDto } from '../userDto';

import { MatSort} from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import {MatTableModule, MatTableDataSource} from '@angular/material/table'; 
import * as JsonToXML from "js2xmlparser";
import * as converter from 'xml-js';
import { UserService } from '../user.service';


@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {


  displayedColumns: string[] = ['No','firstName',"lastName","username","email"];

  listUsers:UserDto[];
  response;
  dataSource;

  constructor(private userService:UserService) {

   }

   @ViewChild(MatSort, { static: true }) sort: MatSort;
   @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  ngOnInit(): void {

    let resp=this.userService.getAllUsers();

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

  applyFilter(filterValue:string)
  {
    this.dataSource.filter=filterValue.trim().toLowerCase();
    this.dataSource.paginator=this.paginator;
  }

}
   // console.log(JsonToXML.parse("data", this.response))

    // var xmlResponse=JsonToXML.parse("data", this.response);
