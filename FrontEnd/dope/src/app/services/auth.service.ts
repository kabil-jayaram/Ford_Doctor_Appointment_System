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
}
