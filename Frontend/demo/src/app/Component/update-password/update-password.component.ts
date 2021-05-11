import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UpdatePassword } from 'src/app/Model/UpdatePassword';
import { AuthService } from 'src/app/Service/Auth/auth.service';
import { PopupUtils } from 'src/app/Utils/PopupUtils';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {

  password: string = '';

  confirmPassword: string = '';

  token: string = '';

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    let tempToken = new URLSearchParams(window.location.search).get('token');
    if (null == tempToken || undefined == tempToken) {
      this.router.navigate(["/login"]);
    }
  }

  /**
   * Update password
   */
  updatePassword() {
    let tempToken = new URLSearchParams(window.location.search).get('token');
    this.token = null != tempToken ? tempToken : '';
    let body = new UpdatePassword(this.token, this.password, this.confirmPassword);

    this.authService.updatePassword(body).subscribe(data => {
      PopupUtils.alertSuccessAndBackToLogin(data.message, this.router);
    }, err => {
      PopupUtils.alertWithFail(err.error.message);
    });
  }

}
