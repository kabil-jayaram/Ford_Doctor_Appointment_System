import { AuthService } from './services/auth.service';
import { LoginComponent } from './authentication/login/login.component';
import { Component } from '@angular/core';
import { BackendApiService } from './services/backend-api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private authService:AuthService,private backendApi:BackendApiService) {

  }

  title = 'dope';
  isLogin:any;
  doctor:boolean = true;
  role:any;
  changeview() {

    this.doctor = !(this.doctor);
  }

  isLoggedIn() {
    return this.authService.getLoginStatus();
  }

  getIsLogin()
  {
    return this.authService.getLoginOrRegiser();
  }

  ngOnInit(): void {
   this.isLogin=this.authService.getLoginOrRegiser();
   this.authService.loginOrRegisterChanged.subscribe((data:any)=>{
    this.isLogin=data;
   })
   this.role=this.backendApi.getRole();
    this.backendApi.getRoleChanged.subscribe((data:any)=>{
    this.role=data;
   })
  }
  
}
