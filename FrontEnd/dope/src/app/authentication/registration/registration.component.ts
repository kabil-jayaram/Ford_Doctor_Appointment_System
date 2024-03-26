import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }

  registerUser(data:any) {
    let user = new User(data.value.uname, data.value.pwd, data.value.email);
    this.authService.register(user);
  }
}
