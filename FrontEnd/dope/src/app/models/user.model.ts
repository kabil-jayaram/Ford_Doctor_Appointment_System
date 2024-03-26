export class User {
  constructor(uname:string, pwd:string, email:string) {
    this.name = uname;
    this.password = pwd;
    this.email = email;
  }

  private name:string;
  private password:string;
  private email:string;
  private roles:string = "USER";
}
