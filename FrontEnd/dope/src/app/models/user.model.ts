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


export class LoginUser {
  private userName: string;
  private password: string;
  constructor(name: string, pwd: string) {
    this.userName = name;
    this.password = pwd;
  }
}

export class LoggedUserDetails {
  private id: number;
  private userName: string;
  private roles: string;
  private authToken: string;
  constructor(id: number, name: string, role: string, token: string) {
    this.id = id;
    this.userName = name;
    this.roles = role;
    this.authToken = token;
  }
}