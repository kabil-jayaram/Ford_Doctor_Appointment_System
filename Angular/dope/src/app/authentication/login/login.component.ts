import { Component, OnInit } from '@angular/core';
import { LoginUser } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }

  loggingIn(data:any)
  {
    let user=new LoginUser(data.value.userName,data.value.password);
    window.sessionStorage.clear()
    this.authService.login(user);
    this.authService.setLoginStatus();
  }
  // lu = new LoginUser("ttt","test");
  register()
  {
    this.authService.setLoginOrRegiser();
    // this.authService.login(this.lu);
  }
}

// private userName: string;
//   private password: string;