import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { AnnouncementDto } from '../announcementsDto';
import { ServerResponseDto } from '../ServerResponse';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AnnouncementService } from '../announcement.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-announcemnets',
  templateUrl: './announcemnets.component.html',
  styleUrls: ['./announcemnets.component.css']
})
export class AnnouncemnetsComponent implements OnInit {

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  
  // private String id;
  // private String userName;
  // private Date date;
  // private String title;
  // private String description;

  displayedColumns: string[] = ['No', 'username', 'date',"title","Action"];

  listAnnouncements:AnnouncementDto[];

  dataSource;
  response;

  constructor(private service: AnnouncementService,
    private _snackBar: MatSnackBar) { }


    
  ngOnInit(): void {

    let resp = this.service.getAllAnnouncements();
    resp.subscribe(data => {

      this.response = data
      this.listAnnouncements = this.response.data;
      this.dataSource = new MatTableDataSource(this.listAnnouncements);

      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      console.log(data);
     
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
