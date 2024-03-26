import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  private isLoggedIn:boolean = false;

  getLoginStatus() {
    return this.isLoggedIn;
  }
}
