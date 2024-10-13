import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap } from 'rxjs';
import { Login } from '../Model/login';
import { SignUp } from '../Model/signUp';
import { StorageService } from './storage.service';
import { Router } from '@angular/router';
import { ResetPassword } from '../Model/resetPassword';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly baseurl: string = 'http://localhost:8085';
  private readonly TOKEN_KEY = 'token';

  constructor(
    private httpClient: HttpClient,
    private storageService: StorageService,
    private router: Router
  ) {}

  signUp(username: string, email: string, password: string): Observable<any> {
    const signup: SignUp = {
      username: username,
      email: email,
      password: password,
      userRoles: ['Customer'],
    };
    return this.httpClient.post<any>(this.baseurl + '/auth/signup', signup);
  }

  adminSignUp(
    username: string,
    email: string,
    password: string
  ): Observable<any> {
    const signup: SignUp = {
      username: username,
      email: email,
      password: password,
      userRoles: ['Admin'],
    };
    return this.httpClient.post<any>(this.baseurl + '/auth/signup', signup);
  }

  logIn(userName: string, Password: string): Observable<any> {
    const login: Login = {
      username: userName,
      password: Password,
      userRoles: ['Customer'],
    };
    // console.log(login);
    return this.httpClient.post<any>(this.baseurl + '/auth/login', login).pipe(
      tap((response) => {
        if (response && response.accessToken) {
          this.storageService.setItem(this.TOKEN_KEY, response.accessToken);
        }
      })
    );
  }

  adminLogIn(userName: string, Password: string): Observable<any> {
    const login: Login = {
      username: userName,
      password: Password,
      userRoles: ['Admin'],
    };

    return this.httpClient.post<any>(this.baseurl + '/auth/login', login).pipe(
      tap((response) => {
        if (response && response.accessToken) {
          this.storageService.setItem(this.TOKEN_KEY, response.accessToken);
        }
      })
    );
  }

  resetPassword(forgotPassword: ResetPassword): Observable<any> {
    return this.httpClient.put<any>(
      this.baseurl + '/auth/resetPassword',
      forgotPassword
    );
  }

  logOut(): void {
    this.storageService.removeItem(this.TOKEN_KEY);
    this.storageService.clear();
  }

  getJwtToken(): string | null {
    return this.storageService.getItem(this.TOKEN_KEY);
  }

  getBaseUrl(): string {
    return this.baseurl;
  }

  isLoggedIn(): boolean {
    const token = this.storageService.getItem(this.TOKEN_KEY);
    return !!token;
  }
}
