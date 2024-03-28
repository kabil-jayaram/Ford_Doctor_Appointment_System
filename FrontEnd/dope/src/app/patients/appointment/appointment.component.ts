import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { BackendApiService } from 'src/app/services/backend-api.service';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {

  appointments:any;
  id:any;
  status="APPROVED";
  name:any;
  constructor(private backendApi:BackendApiService,private auth:AuthService) { 
    this.id = Number(window.sessionStorage.getItem('id'));
  }

  ngOnInit(): void {
    this.id = Number(window.sessionStorage.getItem('id'));
    console.log(this.id);
    this.backendApi.updatedUserId.subscribe(data=>{
      this.id=data;
      console.log("From Appointment Component:",data)
    });
    this.appointments=this.backendApi.getAllPatientAppointments(this.id, this.status);
    this.backendApi.allPatientppointmentsDataChanged.subscribe((data:any)=>{
      this.appointments=data;
      console.log("component: ",this.appointments);
    })
    console.log(this.appointments);
    this.name=this.backendApi.getName();
  }

  // getAllAppointments()
  // {
  //     this.backendApi.getAllPatientAppointments(this.id,this.status);
  // }

}
