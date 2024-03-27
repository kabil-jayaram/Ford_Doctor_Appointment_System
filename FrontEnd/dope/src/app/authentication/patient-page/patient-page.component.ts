import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-patient-page',
  templateUrl: './patient-page.component.html',
  styleUrls: ['./patient-page.component.css']
})
export class PatientPageComponent implements OnInit {

  constructor(private auth:AuthService) { }

  ngOnInit(): void {
  }

  name:string = "Patient";
  logout()
  {
    this.auth.logout();
  }
}
