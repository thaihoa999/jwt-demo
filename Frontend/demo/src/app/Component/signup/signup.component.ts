import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupRes } from 'src/app/Model/Response/SignupRes';
import { Signup } from 'src/app/Model/Signup';
import { AuthService } from 'src/app/Service/Auth/auth.service';
import { PopupUtils } from 'src/app/Utils/PopupUtils';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupEntity: SignupRes = new SignupRes();

  signupForm = this.formBuilder.group({
    userName: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
    email: new FormControl('')
  });

  constructor(private router: Router, private authService: AuthService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
  }

  register() {
    let userName = this.signupForm.value.userName;
    let password = this.signupForm.value.password;
    let confirmPassword = this.signupForm.value.confirmPassword;
    let email = this.signupForm.value.email;
    let entity = new Signup(userName, password, confirmPassword, email);
    this.authService.signup(entity).subscribe(data => {
      PopupUtils.alertSuccessAndBackToLogin(data.message, this.router);
    }, err => {
      PopupUtils.alertWithFail(err.error.message);
    });
  }
}
