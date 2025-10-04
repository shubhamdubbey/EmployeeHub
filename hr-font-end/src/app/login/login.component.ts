import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthenticateUsersService } from '../authenticate-users.service';
import { AuthResponse } from '../../models/authresponse';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  invalidCredentials: boolean = false;
  message: string = '';

  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(
    private router: Router,
    public authenticateUsersService: AuthenticateUsersService
  ) {}

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  checkLogin() {
    const username = this.username?.value;
    const password = this.password?.value;

    this.authenticateUsersService.authenticate(username, password).subscribe(
      (data: AuthResponse) => {
        this.invalidCredentials = false;

        sessionStorage.setItem('username', data.username);
        sessionStorage.setItem('token', 'Bearer ' + data.token);
        sessionStorage.setItem('role', data.role); // ✅ Save role here

        this.router.navigate(['home']);
      },
      (error) => {
        this.invalidCredentials = true;
      }
    );
  }
}
