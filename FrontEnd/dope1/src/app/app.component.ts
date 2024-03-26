import { AuthService } from './services/auth.service';
import { LoginComponent } from './authentication/login/login.component';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private authService:AuthService) {

  }

  title = 'dope';

  doctor:boolean = true;

  changeview() {
    this.doctor = !(this.doctor);
  }

  isLoggedIn() {
    return this.authService.getLoginStatus();
  }
}

console.log(document.body.style.width);
