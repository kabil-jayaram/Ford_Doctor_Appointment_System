import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookAppointmentComponent } from './patients/book-appointment/book-appointment.component';
import { AppointmentsComponent } from './doctors/appointments/appointments.component';
import { ScheduleAppointmentsComponent } from './doctors/schedule-appointments/schedule-appointments.component';
import { DashboardComponent } from './doctors/dashboard/dashboard.component';
import { AppointmentComponent } from './patients/appointment/appointment.component';
import { PatientDashboardComponent } from './patients/patient-dashboard/patient-dashboard.component';
import { HomeComponent } from './authentication/home/home.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'book',component:BookAppointmentComponent},
  {path:'schedule-appointments',component:ScheduleAppointmentsComponent},
  {path:'doctor-appointments',component:AppointmentsComponent},
  {path:'doctor-dashboard',component:DashboardComponent},
  {path:'patient-appointment',component:AppointmentComponent},
  {path:'patient-dashboard',component:PatientDashboardComponent},
  {path:'**',redirectTo:'/home',pathMatch:'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
