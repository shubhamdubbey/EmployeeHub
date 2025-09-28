  import { HttpClient, HttpErrorResponse } from '@angular/common/http';
  import { Injectable } from '@angular/core';
  import { AuthenticateUsers } from '../models/authenticate';
  import { firstValueFrom, catchError, throwError } from 'rxjs';
  
  @Injectable({
    providedIn: 'root'
  })
  export class AuthenticateUsersService {

    constructor(private http:HttpClient) { 
      this.invalidCredentials = false;
    }
    authenticatedUrl : string = "http://localhost:8090/api/authenticate";
    authentiCatedUserBacked : AuthenticateUsers;
    authenticated : boolean;
    authenticateUser : AuthenticateUsers;
    invalidCredentials : boolean = false;
    errorMEssage : string = '';

  //   getAuthenticatedUser(user:AuthenticateUsers){
  //     return firstValueFrom(this.http.post<AuthenticateUsers>(this.authenticatedUrl,user).pipe(
  //       catchError(this.handleError)
  //     ));
  //   }
    
  //   private handleError(error: HttpErrorResponse) {
  //     return throwError(() => new Error("invalid credentials"));
  //   }

  //   async authenticate(userName, passWord){
  //     this.authentiCatedUser = new AuthenticateUsers();
  //     this.authentiCatedUser.userName = userName;
  //     this.authentiCatedUser.passWord = passWord;

  //   this.authentiCatedUserBacked = await this.getAuthenticatedUser(this.authentiCatedUser);

  //   if(this.authentiCatedUser.userName === this.authentiCatedUserBacked.userName){
  //     this.authenticated = true;
  //     sessionStorage.setItem('username',this.authentiCatedUser.userName);
  //   }
  //   else{
  //     this.authenticated = false;
  //   }
  //   return this.authenticated;
  // }

  authenticate(userName : string, password : string){
    this.authenticateUser = new AuthenticateUsers();
    this.authenticateUser.userName = userName;
    this.authenticateUser.passWord = password;

    // this.http.post<AuthenticateUsers>(this.authenticatedUrl,this.authenticateUser).subscribe(
    //   (data) => {
    //     this.authenticateUser = data;
    //     console.log(this.authenticateUser);
    //     sessionStorage.setItem("username",data.userName);
    //   },
    //   (error) => {
    //     this.authenticated = false;
    //   }
    // )

    return this.http.post<AuthenticateUsers>(this.authenticatedUrl,this.authenticateUser);
  }
    isUserLoggedIn(){
      let username = sessionStorage.getItem('username');
      return !(username === null);
    }

    logOut(){
      sessionStorage.removeItem('username');
    }

  }
