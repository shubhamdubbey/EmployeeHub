import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthenticateUsersService } from '../authenticate-users.service';
import { AuthenticateUsers } from '../../models/authenticate';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  invalidCredentials : boolean = false;
  authenticated : string;
  authenticatedUser : AuthenticateUsers;
  authenticatedBackedUser : AuthenticateUsers;
  show = true;
  constructor(private router : Router, public authenticateUsersService:AuthenticateUsersService){}
  message : string;
  loginForm : FormGroup = new FormGroup(
    {
      username : new FormControl('',[Validators.required]),
      password : new FormControl('',[Validators.required]),
    }
  );

  ngOnInit() : void{
  }

  // checkLogin(){
  //   this.authenticateUsersService.authenticate(this.loginForm.get("username").value, this.loginForm.get("password").value).subscribe(
  //     (data) => {
  //       this.invalidCredentials = false;
  //       sessionStorage.setItem("username",data.userName);
  //       this.router.navigate(["home"]);
  //     },
  //     (error) => {
  //       this.invalidCredentials = true;
  //     }
  //   );
  // }
  checkLogin(){
    this.invalidCredentials = false;
        sessionStorage.setItem("username","shubham");
    this.router.navigate(["home"]);
  }
  get password(){
    return this.loginForm.get('password');
  }
  get username(){
    return this.loginForm.get('username');
  }
}
