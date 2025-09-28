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

}
