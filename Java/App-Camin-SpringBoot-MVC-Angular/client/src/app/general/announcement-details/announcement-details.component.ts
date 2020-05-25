import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnnouncementService } from '../announcement.service';
import { AnnouncementDto } from '../announcementsDto';

@Component({
  selector: 'app-announcement-details',
  templateUrl: './announcement-details.component.html',
  styleUrls: ['./announcement-details.component.css']
})
export class AnnouncementDetailsComponent implements OnInit {

  current_announcemnet=new AnnouncementDto();
  response;

  constructor(private route: ActivatedRoute,
    private service: AnnouncementService,
  ) { }

  ngOnInit(): void {
      this.getApp();
  }

  getApp(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.service.getAnnouncement(id)
      .subscribe((data) => {
        this.response = data;
        this.current_announcemnet=this.response.data;


      
      },
      err=>{
        console.log("This is an error")
        console.log(err)

      });
  }

}
