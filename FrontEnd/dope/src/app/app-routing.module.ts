import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookAppointmentComponent } from './patients/book-appointment/book-appointment.component';
import { AppointmentsComponent } from './doctors/appointments/appointments.component';

const routes: Routes = [
  {path:'book',component:BookAppointmentComponent},
  {path:'doctor-appointments',component:AppointmentsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
