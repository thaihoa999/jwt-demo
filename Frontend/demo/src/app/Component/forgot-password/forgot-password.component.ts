import { Component, OnInit } from '@angular/core';
import { MessageResponse } from 'src/app/Model/Response/MessageResponse';
import { AuthService } from 'src/app/Service/Auth/auth.service';
import { PopupUtils } from 'src/app/Utils/PopupUtils';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  email: string = '';

  loading: boolean = false;

  entity: MessageResponse = new MessageResponse();

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  submit() {
    this.loading = true;
    this.authService.confirmForgotPassword(this.email).subscribe(data => {
      this.loading = false;
      PopupUtils.alertWithSuccess(data.message);
    }, err => {
      this.loading = false;
      PopupUtils.alertWithFail(err.error.message);
    });
  }
}
