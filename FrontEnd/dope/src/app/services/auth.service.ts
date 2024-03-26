import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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

  register(user:any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
   });

   this.httpClient.post(this.base_url + "/auth/addUser", JSON.stringify(user), { headers: headers }).subscribe((response) => {
      console.log(JSON.stringify(response));
   });
  }
}
