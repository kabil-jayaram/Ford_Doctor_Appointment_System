import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { BackendApiService } from 'src/app/services/backend-api.service';

@Component({
  selector: 'app-patient-page',
  templateUrl: './patient-page.component.html',
  styleUrls: ['./patient-page.component.css']
})
export class PatientPageComponent implements OnInit {

  constructor(private auth:AuthService,private backendApi:BackendApiService) { }

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
