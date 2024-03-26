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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    DoctorPageComponent,
    PatientPageComponent,
    AppointmentsComponent,
    AppointmentComponent,
    BookAppointmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
