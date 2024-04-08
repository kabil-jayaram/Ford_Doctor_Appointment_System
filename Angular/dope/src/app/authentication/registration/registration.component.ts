import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { LoginUser, User } from 'src/app/models/user.model';
import { BackendApiService } from 'src/app/services/backend-api.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }

  registerUser(data:any)
  {
    let user = new User(data.value.uname, data.value.pwd, data.value.email);
    this.authService.register(user);
    // this.authService.setLoginStatus();
  }
  login()
  {
    this.authService.setLoginOrRegiser();
    // this.authService.login(this.lu);
  }
}
