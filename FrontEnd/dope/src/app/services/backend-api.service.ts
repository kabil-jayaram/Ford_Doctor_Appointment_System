import { EventEmitter, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Appointment, DoctorDto, TimeSlot } from '../models/doctor.model';
import { Observable, map, interval } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  constructor(private httpClient: HttpClient) { }

  private isLoggedIn: boolean = false;
  private base_url: string = "http://localhost:8081";

  allDoctors: any;
  allAppointments: any;
  allAppointmentsToApprove: any;
  allRequestedAppointments: any;
  id:number = 0;

  getToken(): any {
    console.log("tokennnn: ", window.sessionStorage.getItem('token'));
    return window.sessionStorage.getItem('token');
  }
  getNameChanged = new EventEmitter<any>();
  getName(): any {
    console.log("userName: ", window.sessionStorage.getItem('userName'));
    this.getNameChanged.emit(window.sessionStorage.getItem('userName'));
    return window.sessionStorage.getItem('userName');
  }
  getRoleChanged = new EventEmitter<any>();
  getRole(): any {

    return window.sessionStorage.getItem('roles');
  }
  updatedUserId = new EventEmitter();
  getUserId()
  {
    this.id =  Number(window.sessionStorage.getItem('id'));
    this.updatedUserId.emit(this.id);
    console.log(this.id);
    return this.id;
  }
  private headers = new HttpHeaders({
    // 'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0dHQiLCJpYXQiOjE3MTE1MTIxNzksImV4cCI6MTcxMTUxNTc3OX0.4f7ECLdGkURoM_4PAd3zgbWH_OFGh-PD0iQ6OPDH91Q'
    'Authorization': 'Bearer ' + this.getToken()
  });

  allDoctorsDataChanged = new EventEmitter<[]>();

  getAllDoctors() {

    //  this.httpClient.get<DoctorDto>(this.base_url + "/api/doctors", { headers: headers }).subscribe((response) => {
    //     console.log(response);
    //  });
    return this.httpClient.get<any[]>(this.base_url + "/api/doctors", { headers: this.headers })
      .pipe(
        map(data => {
          data.map(item => new DoctorDto(
            item.id,
            item.name,
            item.specialization,
            item.timeSlot.map((slot: { id: number; startTime: string; endTime: string; available: boolean; }) => new TimeSlot(slot.id, slot.startTime, slot.endTime, slot.available))
          ));

          console.log("data: ", data);
          return data;
        })
      )
      .subscribe(allDoc => {
        //console.log(allDoc);
        this.allDoctors = allDoc;
        this.allDoctorsDataChanged.emit(this.allDoctors);
        return this.allDoctors;
      });
  }

  allAppointmentsToApproveDataChanged = new EventEmitter<[]>();

  getAllAppointmentsToApprove(id: number) {
    const url = `${this.base_url}/api/appointments/doctor/${id}`;
    return this.httpClient.get<any[]>(url ,{ headers: this.headers })
    .pipe(
      map(data => {
        data.map(item => new Appointment(
          item.id,
          item.patientId,
          item.doctorId,
          item.timeSlot,
          item.description,
          item.diagnosis,
          (item.status)
        ));
        console.log("data: ", data);
        return data;
      })
    ).subscribe(allAppointments => {
      this.allAppointmentsToApprove = allAppointments;
      this.allAppointmentsToApproveDataChanged.emit(this.allAppointmentsToApprove);
      return this.allAppointmentsToApprove;
    })
  }

  allAppointmentsDataChanged = new EventEmitter<[]>();

  getAllAppointments() {
    return this.httpClient.get<any[]>(this.base_url + "/api/appointments", { headers: this.headers })
    .pipe(
      map(data => {
        data.map(item => new Appointment(
          item.id,
          item.patientId,
          item.doctorId,
          item.timeSlot,
          item.description,
          item.diagnosis,
          (item.status)
        ));
        console.log("data: ", data);
        return data;
      })
    ).subscribe(allAppointments => {
      this.allAppointments = allAppointments;
      this.allAppointmentsDataChanged.emit(this.allAppointments);
      return this.allAppointments;
    })
  }



  allRequestedAppointmentsDataChanged = new EventEmitter<[]>();

  getAllRequestedAppointments(id:number, status:string) {
    let params = new HttpParams()
    .set('doctorId', id)
    .set('status', status);
    return this.httpClient.get<any[]>(this.base_url + "/api/appointments/doctor", { headers: this.headers, params })
    .pipe(
      map(data => {
        data.map(item => new Appointment(
          item.id,
          item.patientId,
          item.doctorId,
          item.timeSlot,
          item.description,
          item.diagnosis,
          (item.status)
        ));
        console.log("data: ", data);
        return data;
      })
    ).subscribe(allAppointments => {
      this.allRequestedAppointments = allAppointments;
      this.allRequestedAppointmentsDataChanged.emit(this.allRequestedAppointments);
      return this.allRequestedAppointments;
    })
  }


  updateAppointmentStatus(appointment: Appointment) {
    return this.httpClient.put(this.base_url + "/api/appointment", appointment, { headers: this.headers }).subscribe(response =>
      console.log(response));
  }

  requestAppointment(docId: number, slotId: number, symptoms: any) {

    this.getUserId();

    let reqAppointment = new Appointment(1,this.id,docId,slotId,symptoms,"","REQUESTED")

    this.httpClient.post<Appointment>(this.base_url+"/api/appointment",reqAppointment,{headers:this.headers})
    .subscribe((response)=>{
      console.log("apointment: ",response);
      alert("Your request has been submitted\n Please wait till get Approval");
    })
  }

  allPatientAppointment:any;
  allPatientppointmentsDataChanged = new EventEmitter<[]>();
  getAllPatientAppointments(id:number, status:string) {
    let params = new HttpParams()
    .set('patientId', id)
    .set('status', status);
    return this.httpClient.get<any[]>(this.base_url + "/api/appointments/patient", { headers: this.headers, params })
    .pipe(
      map(data => {
        data.map(item => new Appointment(
          item.id,
          item.patientId,
          item.doctorId,
          item.timeSlot,
          item.description,
          item.diagnosis,
          (item.status)
        ));
        console.log("patient approved appointments: ", data);
        return data;
      })
    ).subscribe(allAppointments => {
      this.allPatientAppointment = allAppointments;
      this.allPatientppointmentsDataChanged.emit(this.allPatientAppointment);
      return this.allPatientAppointment;
    })
  }



  // getAllDoctors()
  // {
  //   this.fetchAvailableDoctors();
  //   this.allDoctorsDataChanged.emit(this.allDoctors);
  //   console.log("service: ",this.allDoctors);
  //   return this.allDoctors;
  // }

  //  fetchAvailableDoctors (){

  //   try{
  //     this.allDoctors =  this.httpClient
  //     .get<any[]>(this.base_url+"/api/doctors",{headers:this.headers})
  //     .pipe(
  //       map(data => data.map(item => new DoctorDto(
  //         item.id,
  //         item.name,
  //         item.specialization,
  //         item.timeSlot.map((slot: { id: number; startTime: string; endTime: string; available: boolean; }) => new TimeSlot(slot.id, "start", "end", slot.available))
  //       )))).subscribe(data=>{
  //         this.allDoctors=data;
  //         return this.allDoctors;
  //       });

  //       console.log(this.allDoctors);
  //        return this.allDoctors;


  //   }catch{
  //     console.log("error in getting doctors data");
  //   }

  // }

}
