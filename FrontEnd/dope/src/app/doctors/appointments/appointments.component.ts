import { Component, OnInit } from '@angular/core';
import { BackendApiService } from 'src/app/services/backend-api.service';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})

export class AppointmentsComponent implements OnInit {
  allAppointments:any;
  id:number = 0;
  status:string = 'REQUESTED';
  constructor(private backendApi:BackendApiService) {

   }
  ngOnInit(): void {
    this.id = Number(window.sessionStorage.getItem('id'));
    console.log(this.id);
    this.backendApi.updatedUserId.subscribe(data=>{
      this.id=data;
      console.log("From Appointment Component:",data)
    });
    this.allAppointments=this.backendApi.getAllRequestedAppointments(this.id, this.status);
    this.backendApi.allAppointmentsDataChanged.subscribe((data:any)=>{
      this.allAppointments=data;
      console.log("component: ",this.allAppointments);
    })
    console.log(this.allAppointments);
  }

  approveAppointment(appointment: any) {
    appointment.status = 'APPROVED';
    this.backendApi.updateAppointmentStatus(appointment);
   }

   rejectAppointment(appointment: any) {
    appointment.status = 'REJECTED';
    this.backendApi.updateAppointmentStatus(appointment);
   }
}
