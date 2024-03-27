import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { LoginUser } from 'src/app/models/user.model';

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
