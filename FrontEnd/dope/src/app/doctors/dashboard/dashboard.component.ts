import { Component } from '@angular/core';
import { Appointment } from 'src/app/models/doctor.model';
import { BackendApiService } from 'src/app/services/backend-api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  allAppointments:any;
  id:number = 0;
  appointmentsToApproveCount: number = 0;
  approvedAppointmentsCount: number = 0;
  constructor(private backendApi:BackendApiService) { }

  ngOnInit(): void {
    this.id = Number(window.sessionStorage.getItem('id'));
    this.backendApi.updatedUserId.subscribe(data=>{
      console.log("From Appointment Component:",data)
    });
    this.allAppointments=this.backendApi.getAllAppointmentsToApprove(this.id);
    this.backendApi.allAppointmentsToApproveDataChanged.subscribe((data:any)=>{
      this.allAppointments=data;
      this.calculateAppointmentCounts();
      console.log("component: ",this.allAppointments);
    })
    console.log(this.allAppointments);
  }

    calculateAppointmentCounts() {
      this.appointmentsToApproveCount = this.allAppointments.filter((appointment: Appointment) => appointment.status === 'REQUESTED').length;
      this.approvedAppointmentsCount = this.allAppointments.filter((appointment: Appointment) => appointment.status === 'APPROVED').length;
   }
}
