
import { HttpClientModule, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User, LoggedUserDetails, LoginUser } from '../models/user.model';
import { Observable, catchError, map, throwError } from 'rxjs';
import { BackendApiService } from './backend-api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient, private backendApi: BackendApiService) { }

  private isLoggedIn: boolean = false;
  private base_url: string = "http://localhost:8081";

  private isLogin: boolean = false;

  loginOrRegisterChanged = new EventEmitter<any>();
  getLoginOrRegiser() {
    this.loginOrRegisterChanged.emit(this.isLogin);
    this.backendApi.getNameChanged.emit(window.sessionStorage.getItem('userName'));
    return this.isLogin;
  }
  setLoginOrRegiser() {
    this.isLogin = !this.isLogin;
    this.loginOrRegisterChanged.emit(this.isLogin);
    this.backendApi.getNameChanged.emit(window.sessionStorage.getItem('userName'));
    return this.isLogin;
  }

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
      if(response == user)
        this.setLoginOrRegiser();
      alert("Registered Successfully!!!")
    });
  }

  // login(user:LoginUser) {
  //   this.httpClient.post<String>(this.base_url + "/auth/login",user).subscribe((response) => {
  //      console.log(response);
  //   });
  //  }

  userData: any;
  // login(auth: LoginUser): void {
  //   const headers = new HttpHeaders().set('Content-Type', 'application/json');

  //   this.httpClient.post<string>(this.base_url + "/auth/login", auth, { headers: headers, responseType: 'text' as 'json' })
  //     .pipe(
  //       catchError((error: HttpErrorResponse) => {
  //         console.error('Error:', error);
  //         return throwError(error);
  //       })
  //     )
  //     .subscribe(
  //       token => {
  //         this.token = token;
  //         alert("user logged successfully!");
  //         console.log('JWT authentitcation Token:', token);
  //         this.saveTokenToBrowser();
  //       },
  //       error => {
  //         alert("error login user : (");
  //         console.error('Login Error:', error);
  //       }
  //     );
  // }

  login(auth: LoginUser): void {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    this.httpClient.post<LoggedUserDetails>(this.base_url + "/auth/login", auth)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.error('Error:', error);
          return throwError(error);
        })
      ).subscribe(
        userDetails => {

          console.log(userDetails);
          this.userData = userDetails;
          this.saveTokenToBrowser();
          this.backendApi.getUserId();
          alert("user logged successfully!");
          let role = window.sessionStorage.getItem('roles');
          this.backendApi.getRoleChanged.emit(role);
          this.backendApi.getName();
        }
      )

  }

  saveTokenToBrowser() {
    window.sessionStorage.setItem('token', this.userData.authToken);
    window.sessionStorage.setItem('id', this.userData.id);
    window.sessionStorage.setItem('userName', this.userData.userName);
    window.sessionStorage.setItem('roles', this.userData.roles);
  }

  logout() {
    window.sessionStorage.clear();
    alert("user logged out successfully!");
  }

}

