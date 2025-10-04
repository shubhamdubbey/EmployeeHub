import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticateUsers } from '../models/authenticate';
import { AuthResponse } from '../models/authresponse';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateUsersService {

  private authenticatedUrl: string = "http://localhost:8090/api/login";

  constructor(private http: HttpClient) {}

  authenticate(userName: string, password: string): Observable<AuthResponse> {
    console.log(userName + " " + password);
    const authRequest: AuthenticateUsers = {
      username: userName,
      password: password
    };

    return this.http.post<AuthResponse>(this.authenticatedUrl, authRequest);
  }

  isUserLoggedIn(): boolean {
    return sessionStorage.getItem('username') !== null;
  }

  getUserRole(): string {
    return sessionStorage.getItem('role') || '';
  }

  hasRole(requiredRoles: string[]): boolean {
    const userRole = this.getUserRole();
    return requiredRoles.some(role => userRole.includes(role));
  }

  logOut(): void {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('role');
  }
}
