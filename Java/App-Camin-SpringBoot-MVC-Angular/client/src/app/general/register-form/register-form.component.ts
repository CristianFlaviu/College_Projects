import { Component, OnInit } from '@angular/core';
import { UserDto } from '../userDto';
import { UserService } from '../user.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { RoomService } from '../room.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  formGroup:FormGroup;



  userDto: UserDto=new UserDto();
  responseDto;


  constructor(private service:UserService,
              private snackBar:MatSnackBar,
              private router:Router,
              private formBuilder:FormBuilder) { }

  ngOnInit(): void {
 
    this.formGroup=this.formBuilder.group({

      firstName:['',[Validators.required,Validators.minLength(5)]],
      lastName:['',[Validators.required,Validators.minLength(5)]],
      email:['',[Validators.required,Validators.minLength(7)]],
      roomNumber:['',[Validators.required]],
      username:['',[Validators.required,Validators.minLength(5)]],
      password:['',[Validators.required,Validators.minLength(5)]]
    })
   
  }

    get firstName(){return this.formGroup.get("firstName");}
    get lastName(){return this.formGroup.get("lastName");}
    get email(){return this.formGroup.get("email");}
    get username(){return this.formGroup.get("username");}
    get roomNumber(){return this.formGroup.get("roomNumber");}
    get password(){return this.formGroup.get("password");}

  register()
  {   
   
    this.userDto.email=this.formGroup.value.email;
    this.userDto.firstName=this.formGroup.value.firstName;
    this.userDto.lastName=this.formGroup.value.lastName;
    this.userDto.username=this.formGroup.value.username;
    this.userDto.roomNumber=this.formGroup.value.roomNumber;
    this.userDto.password=this.formGroup.value.password;
    
    
    let resp=this.service.register(this.userDto);
    
    resp.subscribe(data=>{
      console.log(data);
      this.responseDto=data,
      this.snackBar.open("Successfully created new account","",{duration:2000,
      panelClass:'blue-snackbar'});

      this.router.navigate(["/login"]);

    
    },
    err=>{
      console.log(err);
      this.responseDto=err,
      this.snackBar.open(this.responseDto.error.message,"",{duration:2000,
      panelClass:'red-snackbar'});
      }
    )
  }

}
