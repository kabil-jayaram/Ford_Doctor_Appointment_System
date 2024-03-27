import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-doctor-page',
  templateUrl: './doctor-page.component.html',
  styleUrls: ['./doctor-page.component.css']
})
export class DoctorPageComponent implements OnInit {

  constructor(private auth:AuthService) { }

  ngOnInit(): void {
  }

  name:string = "Doctor";
  logout()
  {
    this.auth.logout();
  }
}
