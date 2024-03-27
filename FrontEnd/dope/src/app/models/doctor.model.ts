export class DoctorDto{
    private id:number;
    private name:string;
    private specialization:string;
    private timeSlot:TimeSlot[];
    constructor(id:number,name:string,spec:string,slot:TimeSlot[])
    {
        this.id=id;
        this.name=name;
        this.specialization=spec;
        this.timeSlot=slot;
    }


}

export class TimeSlot{
    private id:number;
    private startTime:string;
    private endTime:string;
    private available:boolean;

    constructor(id:number,st:string,et:string,avail:boolean)
    {
        this.id=id;
        this.startTime=st;
        this.endTime=et;
        this.available=avail;
    }
}

export class Appointment {
  private id:number;
  private patientId:number;
  private doctorId:number;
  private timeSlot:number;
  private description:string;
  private diagnosis:string;
  private status:string;

  constructor(id:number, pid:number, docid:number, ts:number, des:string, dia:string, status:string) {
    this.id = id;
    this.patientId = pid;
    this.doctorId = docid;
    this.timeSlot = ts;
    this.description = des;
    this.diagnosis = dia;
    this.status = status;
  }
}
