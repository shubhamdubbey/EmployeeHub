import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticateUsersService } from './authenticate-users.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateGuardService implements CanActivate{

  constructor(private router: Router,
    private authService: AuthenticateUsersService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : boolean{
    if (this.authService.isUserLoggedIn()){
      return true;
    }
    else{
        this.router.navigate(['login']);
        return false;
    }

  }
}
