import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthenticateUsersService } from '../authenticate-users.service';
import { AuthResponse } from '../../models/authresponse';
import { PasswordChangeRequest } from '../../models/passwordchangerequest';
import { HumanResourceService } from '../human-resource.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  invalidCredentials = false;
  showChangePassword = false;
  isFirstLogin = false;
  manualChange = false;
  passwordMismatch = false;
  passwordChangeError = '';
  passwordChangeSuccess = '';
  showLoginButton = false;
  loading = false;
  redirecting = false;

  loginForm!: FormGroup;
  changePasswordForm!: FormGroup;

  constructor(
    private router: Router,
    public authenticateUsersService: AuthenticateUsersService,
    private humanResourceService: HumanResourceService
  ) {}

  ngOnInit(): void {
    this.initForms();
    this.resetState();
  }

  private initForms(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });

    this.changePasswordForm = new FormGroup({
      currentPassword: new FormControl('', [Validators.required]),
      newPassword: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required]),
    });
  }

  private resetState(): void {
    this.invalidCredentials = false;
    this.showChangePassword = false;
    this.isFirstLogin = false;
    this.manualChange = false;
    this.passwordMismatch = false;
    this.passwordChangeError = '';
    this.passwordChangeSuccess = '';
    this.showLoginButton = false;
    this.loading = false;
    this.redirecting = false;
  }

  get username() { return this.loginForm.get('username'); }
  get password() { return this.loginForm.get('password'); }

  /**
   * Handles login and detects first login users
   */
  checkLogin(): void {
    const username = this.username?.value;
    const password = this.password?.value;

    this.loading = true;
    this.authenticateUsersService.authenticate(username, password).subscribe({
      next: (data: AuthResponse) => {
        this.invalidCredentials = false;
        this.loading = false;

        sessionStorage.setItem('token', 'Bearer ' + data.token);

        if (data.firstLogin === true) {
          // First login, show change password UI
          this.showChangePassword = true;
          this.isFirstLogin = true;
          this.manualChange = false;
          sessionStorage.setItem('tempUser', username);
        } else {
          // Normal login flow
          sessionStorage.setItem('username', data.username);
          sessionStorage.setItem('role', data.role);
          this.router.navigate(['home']);
        }
      },
      error: () => {
        this.loading = false;
        this.invalidCredentials = true;
      }
    });
  }

  /**
   * Opens the password change screen manually
   */
  openChangePasswordManually(): void {
    setTimeout(() => {
      this.resetState();
      this.manualChange = true;
      this.showChangePassword = true;
      this.isFirstLogin = false;
      sessionStorage.clear();
    }, 0);
  }

  /**
   * Handles password change for first-time users or manual change
   */
  onChangePassword(): void {
    this.passwordMismatch = false;
    this.passwordChangeError = '';
    this.passwordChangeSuccess = '';
    this.showLoginButton = false;

    const currentPassword = this.changePasswordForm.value.currentPassword;
    const newPassword = this.changePasswordForm.value.newPassword;
    const confirmPassword = this.changePasswordForm.value.confirmPassword;

    if (newPassword !== confirmPassword) {
      this.passwordMismatch = true;
      return;
    }

    const tempUser = sessionStorage.getItem('tempUser');
    const token = sessionStorage.getItem('token');
    const usernameForManual = this.loginForm.value.username;

    const dto: PasswordChangeRequest = {
      username: this.isFirstLogin ? (tempUser ?? '') : usernameForManual,
      password: currentPassword,
      newPassword,
      newPasswordConfirm: confirmPassword
    };

    this.loading = true;

    this.humanResourceService.changePassword(dto, token).subscribe({
      next: (res) => {
        console.log('Am inside it.', res);
        this.loading = false;
    
        const message = res?.message?.toLowerCase().trim() || '';
        if (message === 'success') {
          this.passwordChangeSuccess = 'Password changed successfully!';
          this.redirecting = true;
          this.changePasswordForm.reset();
          sessionStorage.clear();
    
          setTimeout(() => {
            this.redirecting = false;
            this.resetState();
            this.showChangePassword = false;
            this.loginForm.reset();
          }, 2000);
        } else {
          this.passwordChangeError = message || 'Password change failed!';
        }
      },
      error: (err) => {
        console.error('Unexpected error', err);
        this.loading = false;
        this.passwordChangeError = 'Password change failed!';
      }
    });
  }    
  /**
   * Redirects back to login page
   */
  goToLogin(): void {
    this.resetState();
    this.loginForm.reset();
    this.changePasswordForm.reset();
    sessionStorage.clear();

    setTimeout(() => {
      this.showChangePassword = false;
    }, 50);
  }
}
