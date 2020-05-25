import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';
import { UserDto } from '../userDto';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-request-change-pass',
  templateUrl: './request-change-pass.component.html',
  styleUrls: ['./request-change-pass.component.css']
})
export class RequestChangePassComponent implements OnInit {


  formGroup:FormGroup;
  load="0"
  constructor(private formBuilder:FormBuilder,
              private userService:UserService,
              private authService:AuthService,
              private route:Router,
              private snackBar:MatSnackBar) { }

  ngOnInit(): void {

 

    this.formGroup=this.formBuilder.group({
      email:['',[Validators.required,Validators.minLength(5)]]
    });
  }

  get email(){return this.formGroup.get("email")}

  submit()
  {
    let user=new UserDto();

    this.load="1"
    let email2=this.formGroup.value.email;
    this.userService.genereteResetPassToken(email2).subscribe(data=>{
   
      this.route.navigate(["/resetPassword"])
    },
    err=>{
      this.load="0"
      // this.snackBar.open(this.response.error.message,"",{duration:2000,
      //   panelClass:'red-snackbar'});
      this.snackBar.open("email does not exist in our database","",{duration:2000,
                        panelClass:"red-snackbar"});
    });
  }

}
