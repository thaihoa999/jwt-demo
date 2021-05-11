import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/Model/Login';
import { LoginRes } from 'src/app/Model/Response/LoginRes';
import { AuthService } from 'src/app/Service/Auth/auth.service';
import { PopupUtils } from 'src/app/Utils/PopupUtils';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hasError: boolean = false;

  message: string = '';

  response: LoginRes = new LoginRes();

  loginForm: FormGroup = this.formBuilder.group( {
    userName: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  constructor(private router: Router, 
    private authService : AuthService,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    
  }

  /**
   * Login
   */
  login() {
    let userName = this.loginForm.value.userName;
    let password = this.loginForm.value.password;
    let entity = new Login(userName, password);

    this.authService.login(entity).subscribe(data => {
      this.response.token = data.token;
      localStorage.setItem('token', data.token);
      PopupUtils.alertWithSuccess(this.response.message).then(okay => {
        if (okay) {
          this.router.navigate(['/user-list']);
        }
      });
    }, err => {
      PopupUtils.alertWithFail(err.error.message);
    });
  }

  clear() {
    this.loginForm.reset();
  }
}
