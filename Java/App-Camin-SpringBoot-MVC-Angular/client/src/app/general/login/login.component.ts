import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { UserDto } from '../userDto';
import { decode } from 'punycode';
import { trigger } from '@angular/animations';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../auth.service';
import { FormGroup, FormControl } from '@angular/forms';
// const bodyParser = require('body-parser');



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });


  message: any
  response: any;
  token:any;
  error:any;

  constructor(private serviceAuth:AuthService, 
    private service: UserService, 
    private router: Router,
    public jwtHelper: JwtHelperService,
    private snackBar:MatSnackBar) { }

  ngOnInit() {
  
  }

  doLogin() {

    localStorage.removeItem("role");
    localStorage.removeItem("token");

    let loginUser = new UserDto();
    loginUser.password = this.form.value.password;
    loginUser.email = this.form.value.email;

    console.log(loginUser);

    this.service.login(loginUser).subscribe((data) => {

      console.log(data);
      this.response = data;
      this.serviceAuth.setSesion(this.response.data);
      this.router.navigate(["/home"])

      this.snackBar.open("Succesfully registered ","",{duration:2000,
        panelClass:'blue-snackbar'});
    

    },
    err=>{
      this.response=err;
      this.snackBar.open(this.response.error.message,"",{duration:2000,
        panelClass:'red-snackbar'});
    });
  }

}
