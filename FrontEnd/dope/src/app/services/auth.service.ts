
import { HttpClientModule, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable, catchError, map, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  private isLoggedIn: boolean = false;
  private base_url: string = "http://localhost:8081";

  getLoginStatus() {
    return this.isLoggedIn;
  }
  setLoginStatus() {
    this.isLoggedIn = !this.isLoggedIn;
    return this.isLoggedIn;
  }

  register(user: User) {
    this.httpClient.post<User>(this.base_url + "/auth/addUser", user).subscribe((response) => {
      console.log(response);
    });
  }

  // login(user:LoginUser) {
  //   this.httpClient.post<String>(this.base_url + "/auth/login",user).subscribe((response) => {
  //      console.log(response);
  //   });
  //  }

  token: any;
  login(auth: LoginUser): void {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    this.httpClient.post<string>(this.base_url + "/auth/login", auth, { headers: headers, responseType: 'text' as 'json' })
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.error('Error:', error);
          return throwError(error);
        })
      )
      .subscribe(
        token => {
          this.token = token;
          alert("user logged successfully!");
          console.log('JWT authentitcation Token:', token);
          this.saveTokenToBrowser();
        },
        error => {
          alert("error login user : (");
          console.error('Login Error:', error);
        }
      );
  }

  saveTokenToBrowser() {
    window.sessionStorage.setItem('token', this.token);
  }

  logout() {
    window.sessionStorage.clear();
    alert("user logged out successfully!");
  }

}

export class LoginUser {
  private userName: string;
  private password: string;
  constructor(name: string, pwd: string) {
    this.userName = name;
    this.password = pwd;
  }
}

