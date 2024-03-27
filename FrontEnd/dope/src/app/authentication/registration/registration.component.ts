import { Component, OnInit } from '@angular/core';
import { AuthService,LoginUser } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }

  registered(data:any)
  {
    this.authService.setLoginStatus();

  }
  lu = new LoginUser("ttt","test");
  login()
  {
    
    this.authService.login(this.lu);
  }
}
