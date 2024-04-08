import { Component, OnInit } from '@angular/core';
import { DoctorDto, TimeSlot } from 'src/app/models/doctor.model';
import { BackendApiService } from 'src/app/services/backend-api.service';

@Component({
  selector: 'app-book-appointment',
  templateUrl: './book-appointment.component.html',
  styleUrls: ['./book-appointment.component.css']
})
export class BookAppointmentComponent implements OnInit {

  allDoctors:any;
  constructor(private backendApi:BackendApiService) {
    
   }

  // ngOnInit(): void {
  //   this.allDoctors=this.backendApi.getAllDoctors();
  //   console.log("Doctors: ",this.allDoctors);
  // }

  ngOnInit(): void {
    this.allDoctors=this.backendApi.getAllDoctors();
    this.backendApi.allDoctorsDataChanged.subscribe((data:any)=>{
      this.allDoctors=data;
      console.log("component: ",this.allDoctors);
    })
    console.log(this.allDoctors);
  }

  slots:any;
  toggle:boolean=false;
  selectedDocId:any;
  showAppointment(id:any)
  {
    this.toggle=!this.toggle;
    
    const doctor = this.allDoctors.find((doctor: { id: any, timeSlot: any }) => {
      return doctor.id === id;
   });
  this.selectedDocId=id;
   this.slots = doctor ? doctor.timeSlot : undefined;
   console.log(this.slots);
  }

  requestSlot(docId:number,slotId:number)
  {
    let symptoms=prompt("Provide Your Symptoms: ");
    this.backendApi.requestAppointment(docId,slotId,symptoms);
    
  }

}

