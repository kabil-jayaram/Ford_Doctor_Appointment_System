import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookAppointmentComponent } from './patients/book-appointment/book-appointment.component';
import { AppointmentsComponent } from './doctors/appointments/appointments.component';
import { ScheduleAppointmentsComponent } from './doctors/schedule-appointments/schedule-appointments.component';
import { DashboardComponent } from './doctors/dashboard/dashboard.component';

const routes: Routes = [
  {path:'book',component:BookAppointmentComponent},
  {path:'schedule-appointments',component:ScheduleAppointmentsComponent},
  {path:'doctor-appointments',component:AppointmentsComponent},
  {path:'doctor-dashboard',component:DashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
