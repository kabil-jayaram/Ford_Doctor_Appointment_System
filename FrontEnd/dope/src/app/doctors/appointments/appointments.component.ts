import { Component, OnInit } from '@angular/core';
import { BackendApiService } from 'src/app/services/backend-api.service';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})

export class AppointmentsComponent implements OnInit {
  allAppointments:any;
  id:number;
  status:string;
  constructor(private backendApi:BackendApiService) {

   }
  ngOnInit(): void {
    this.allAppointments=this.backendApi.getAllRequestedAppointments(id, status);
    this.backendApi.allAppointmentsDataChanged.subscribe((data:any)=>{
      this.allAppointments=data;
      console.log("component: ",this.allAppointments);
    })
    console.log(this.allAppointments);
  }

}
