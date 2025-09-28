import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Users } from '../models/users';
import { Grades } from '../models/grade';
import { catchError, throwError } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class HumanResourceService {

  empId : number;

  private retrieveEmployeesURL = "http://localhost:8090/api/getEmployees";
  private deleteEmployeeURL = "http://localhost:8090/api/employees";
  private registerEmployeeURL = "http://localhost:8090/api/employees";
  private retirieveEmployeeByIdURL = "http://localhost:8090/api/employees";
  private updateEmployeesGradeURL = "http://localhost:8090/api/employees";
  private retrieveGradesURL = "http://localhost:8090/api/grades";
  
  constructor(private http:HttpClient) { }

  public getEmployeeById(users:Users){
    this.empId = users.employeeId;
    return this.http.get<Users>(this.retirieveEmployeeByIdURL + '/' + this.empId);
  }

  public deleteEmployee(id:number){
    return this.http.delete(this.deleteEmployeeURL+"/"+id).pipe(
      catchError(this.handleError)
    );
  }

  public getEmployees(){
    return this.http.get<Users[]>(this.retrieveEmployeesURL);
  }

  public getGrades(){
    return this.http.get<Grades[]>(this.retrieveGradesURL);
  }

  public registerEmployee(users:Users) {
    return this.http.post(this.registerEmployeeURL, users).pipe(
      catchError(this.handleError)
    );
  }

  public updateEmployee(users:Users, gradeId : number){
    this.empId = users.employeeId;
    return this.http.put(this.updateEmployeesGradeURL + '/' + this.empId +'/' + gradeId, users).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {

    let message = '';

    if (error.status === 0) {
      message = "Some error occured, try again later";
    } else {
      message = `${error.error}`;
      
    }
    return throwError(() => new Error(message));
  }
}
