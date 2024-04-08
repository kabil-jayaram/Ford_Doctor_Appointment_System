import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { BackendApiService } from 'src/app/services/backend-api.service';

@Component({
  selector: 'app-doctor-page',
  templateUrl: './doctor-page.component.html',
  styleUrls: ['./doctor-page.component.css']
})
export class DoctorPageComponent implements OnInit {
  allAppointments:any;
  constructor(private backendApi:BackendApiService,private auth:AuthService) { }
  name:string = "Patient";

  ngOnInit(): void {
    this.name=this.backendApi.getName();
    this.backendApi.getNameChanged.subscribe((data)=>{
      console.log("page: ",data);
      this.name=data;
    })
  }
  logout()
  {
    this.auth.logout();
    
  }

}
