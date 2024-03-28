import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegistrationComponent } from './authentication/registration/registration.component';
import { DoctorPageComponent } from './authentication/doctor-page/doctor-page.component';
import { PatientPageComponent } from './authentication/patient-page/patient-page.component';
import { AppointmentsComponent } from './doctors/appointments/appointments.component';
import { AppointmentComponent } from './patients/appointment/appointment.component';
import { BookAppointmentComponent } from './patients/book-appointment/book-appointment.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ScheduleAppointmentsComponent } from './doctors/schedule-appointments/schedule-appointments.component';
import { DashboardComponent } from './doctors/dashboard/dashboard.component';
import { PatientDashboardComponent } from './patients/patient-dashboard/patient-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    DoctorPageComponent,
    PatientPageComponent,
    AppointmentsComponent,
    AppointmentComponent,
    BookAppointmentComponent,
    ScheduleAppointmentsComponent,
    DashboardComponent,
    PatientDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
