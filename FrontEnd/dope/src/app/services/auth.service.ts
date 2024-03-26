import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient:HttpClient) { }

  private isLoggedIn:boolean = false;
  private base_url:string = "http://localhost:8081";

  getLoginStatus() {
    return this.isLoggedIn;
  }
  setLoginStatus() {
    this.isLoggedIn = !this.isLoggedIn;
    return this.isLoggedIn;
  }

  register(user:User) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
   });

   this.httpClient.post<User>(this.base_url + "/auth/addUser",user).subscribe((response) => {
      console.log(response);
   });
  }
}
