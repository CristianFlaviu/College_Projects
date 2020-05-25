import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(public snackBar:MatSnackBar,public service:AuthService) { }

  ngOnInit(): void {

    this.snackBar.open("LogOut Succesfully","",{duration:2000,
      panelClass:'green-snackbar'});

      this.service.logOut();
  }

}
