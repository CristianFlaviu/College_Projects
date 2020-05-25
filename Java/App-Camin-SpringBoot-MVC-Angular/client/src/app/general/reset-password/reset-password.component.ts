import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { passwordMatchValidator } from './validatorMatcher';
import { UserDto } from '../userDto';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {



  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private jwtHelper: JwtHelperService,
    private snackBar: MatSnackBar,
    private authService: AuthService) { }

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      email: ['', [Validators.required, Validators.minLength(5)]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      password2: ['', [Validators.required]],
      token: ['', [Validators.required]]
    }, { validator: passwordMatchValidator });
  }

  /* Shorthands for form controls (used from within template) */
  get password() { return this.formGroup.get('password'); }
  get password2() { return this.formGroup.get('password2'); }
  get email() { return this.formGroup.get("email"); }
  get token() { return this.formGroup.get("token"); }

  /* Called on each input in either password field */
  onPasswordInput() {
    if (this.formGroup.hasError('passwordMismatch'))
      this.password2.setErrors([{ 'passwordMismatch': true }]);
    else
      this.password2.setErrors(null);
  }

  snackbar_message(color: String, message: string) {
    this.snackBar.open(message, "", {
      duration: 2000,
      panelClass: color + "-snackbar"
    });
  }
  submit() {

    // console.log(this.formGroup.value.token)
    try {


      if (!this.jwtHelper.isTokenExpired(this.formGroup.value.token)) {

        let user = new UserDto();
        user.id = this.formGroup.value.token;
        user.email = this.formGroup.value.email;
        user.password = this.formGroup.value.password;

        this.userService.setNewPassword(user).subscribe(data => {
          this.snackbar_message("blue", "Password reseted")
        }
          , err => {
            this.snackbar_message("red", err.error.message);

          }
        )
      }
      else {
        if (this.formGroup.value.token) {
          this.snackbar_message("red", "EXPIRED TOKEN")

          let token = this.formGroup.value.token;
          
          console.log(this.authService.decedode(token))
        }
        else {
          this.snackbar_message("red", "Empty Token")
        }
      }
    }
    catch (err) {
      this.snackbar_message("red", "INVALID TOKEN")

    }
  }
}
