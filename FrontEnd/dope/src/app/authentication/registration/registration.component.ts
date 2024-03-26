import { Component, OnInit } from '@angular/core';
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

  registerUser(data:any)
  {
    this.authService.setLoginStatus();
  }

}
