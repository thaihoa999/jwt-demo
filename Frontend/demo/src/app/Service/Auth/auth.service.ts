import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { Login } from 'src/app/Model/Login';
import { LoginRes } from 'src/app/Model/Response/LoginRes';
import { MessageResponse } from 'src/app/Model/Response/MessageResponse';
import { SignupRes } from 'src/app/Model/Response/SignupRes';
import { Signup } from 'src/app/Model/Signup';
import { UpdatePassword } from 'src/app/Model/UpdatePassword';
import { User } from 'src/app/Model/User';
import { BaseService } from '../Base/base.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends BaseService {
  constructor(private http: HttpClient) {
    super();
  }

  /**
   * Login to system
   * 
   * @param body 
   * @returns 
   */
  login(body: Login) {
    let url = AUTH_URL.concat('/login');

    return this.http.post<LoginRes>(url, body);
  }

  isLoggedIn(): boolean {
    // let jwtHelper = new JwtHelperService();
    let token = localStorage.getItem('token');

    return token ? true : false;
  }

  /**
   * Signup user
   * 
   * @param body
   * @returns 
   */
  signup(body: Signup) {
    let url = AUTH_URL.concat('/signup');

    return this.http.post<SignupRes>(url, body);
  }

  confirmForgotPassword(email: string) {
    let url = AUTH_URL.concat("/confirmResetPassword");

    return this.http.post<MessageResponse>(url, { email });
  }

  getAllUser(): Observable<User[]> {
    let url = USER_URL.concat('/getAllUser');
    let header = this.getHttpHeaders(localStorage.getItem('token'));
    
    return this.http.get<User[]>(url, { 'headers': header });
  }

  updatePassword(body: UpdatePassword) {
    let url = AUTH_URL.concat('/changePassword');
    // let body = { newPassword, confirmNewPassword };

    return this.http.post<MessageResponse>(url, body);
  }
}

export const BASE_URL = 'http://localhost:8080/api';
export const AUTH_URL = BASE_URL.concat('/auth');
export const USER_URL = BASE_URL.concat('/user');
