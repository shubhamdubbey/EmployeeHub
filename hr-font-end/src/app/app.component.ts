import { Component } from '@angular/core';
import { AuthenticateUsersService } from './authenticate-users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  title = 'HumanResource';
  authenticated : boolean = true;

  // constructor(private router : Router, public autheticateUsersService : AuthenticateUsersService){
  //   if(this.autheticateUsersService.isUserLoggedIn()){
  //     this.router.navigate(['home']);
  //   }
  // }

  constructor(private router : Router, public autheticateUsersService : AuthenticateUsersService){
      this.router.navigate(['home']);
    
  }

  ngOnInit(){
  }

  logout(){
    this.autheticateUsersService.logOut();
    this.router.navigate(['login']);
  }

  getEmployeeIdLastDigits(): string {
    const empId = sessionStorage.getItem('username');
    if (empId) {
      // last two digits
      return empId.slice(-2);
    }
    return '?';
  }
  
  getAvatarColor(): string {
    const empId = sessionStorage.getItem('username');
    if (empId) {
      const hash = empId.split('').reduce((acc, char) => acc + char.charCodeAt(0), 0);
      const hue = hash % 360;
      return `hsl(${hue}, 60%, 50%)`;
    }
    return '#6c757d'; // fallback gray
  }
}
