import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Model/User';
import { AuthService } from 'src/app/Service/Auth/auth.service';
import { NavigationUtils } from 'src/app/Utils/NavigationUtils';
import { PopupUtils } from 'src/app/Utils/PopupUtils';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[] = [];

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData() {
    if (this.authService.isLoggedIn()) {
      this.authService.getAllUser().subscribe(data => {
        this.users = data;
        this.destroyToken();
      }, err => {
        this.destroyToken();
        PopupUtils.alertWithFail(err.error.message);
      });
    } else {
      NavigationUtils.navigateToLogin(this.router);
    }
  }

  private destroyToken() {
    localStorage.removeItem('token');
  }
}
