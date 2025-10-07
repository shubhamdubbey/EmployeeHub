import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Users } from '../models/users';
import { Grades } from '../models/grade';
import { Observable, catchError, throwError } from 'rxjs';
import { LeaveBalance } from '../models/leavebalance';
import { ApplyLeaveRequest } from '../models/applyleavesrequest';
import { Approval, ApprovalStatus } from '../models/approvals';

@Injectable({
  providedIn: 'root'
})
export class HumanResourceService {

  empId: number;

  private retrieveEmployeesURL = "http://localhost:8090/api/getEmployees";
  private deleteEmployeeURL = "http://localhost:8090/api/deleteEmployee";
  private registerEmployeeURL = "http://localhost:8090/api/addEmployee";
  private retirieveEmployeeByIdURL = "http://localhost:8090/api/getEmployeeById";
  private updateEmployeesGradeURL = "http://localhost:8090/api/updateEmployeesGrade";
  private retrieveGradesURL = "http://localhost:8090/api/grades";
  private checkLeaveBalance = "http://localhost:8090/api/getLeaves";
  private applyLeaves = "http://localhost:8090/api/applyLeaves";
  private getHistoricalLeave = "http://localhost:8090/api/getLeavesHistory";
  private updateManager = "http://localhost:8090/api/updateManager";
  private listOfApprovalsPending = "http://localhost:8090/api/listOfApproval";
  private updateApproval = "http://localhost:8090/api/updateApproval";

  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('token'); // "Bearer <jwt>"
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? token : ''
    });
  }

  public getLeaveBalances(id : number){
    return this.http.get<LeaveBalance>(`${this.checkLeaveBalance}/${id}`, {
      headers: this.getAuthHeaders()
    }); 
  }

  public getEmployeeById(users: Users) {
    this.empId = users.employeeId;
    return this.http.get<Users>(`${this.retirieveEmployeeByIdURL}/${this.empId}`, {
      headers: this.getAuthHeaders()
    });
  }

  public deleteEmployee(id: number) {
    return this.http.delete(`${this.deleteEmployeeURL}/${id}`, {
      headers: this.getAuthHeaders()
    }).pipe(
      catchError(this.handleError)
    );
  }

  public getEmployees() {
    return this.http.get<Users[]>(this.retrieveEmployeesURL, {
      headers: this.getAuthHeaders()
    });
  }

  public getLeavesHistory(id : number) {
    return this.http.get<ApplyLeaveRequest[]>(`${this.getHistoricalLeave}/${id}`, {
      headers: this.getAuthHeaders()
    });
  }

  public getApprovalsList(id : number) {
    return this.http.get<Approval[]>(`${this.listOfApprovalsPending}/${id}`, {
      headers: this.getAuthHeaders()
    });
  }

  public getGrades() {
    return this.http.get<Grades[]>(this.retrieveGradesURL, {
      headers: this.getAuthHeaders()
    });
  }

  public registerEmployee(users: Users) {
    return this.http.post(this.registerEmployeeURL, users, {
      headers: this.getAuthHeaders()
    }).pipe(
      catchError(this.handleError)
    );
  }

  public applyLeave(applyLeaveRequest: ApplyLeaveRequest) {
    return this.http.post(this.applyLeaves, applyLeaveRequest, {
      headers: this.getAuthHeaders()
    }).pipe(
      catchError(this.handleError)
    );
  }

  public updateEmployee(users: Users, gradeId: number) {
    this.empId = users.employeeId;
    return this.http.put(`${this.updateEmployeesGradeURL}/${this.empId}/${gradeId}`, users, {
      headers: this.getAuthHeaders()
    }).pipe(
      catchError(this.handleError)
    );
  }

  updateApprovalStatus(id: string, status: ApprovalStatus): Observable<any> {
    return this.http.put<any>(`${this.updateApproval}/${id}/${status}`, null, {
      headers: this.getAuthHeaders()
    });
  }

  public updateHomeManager(managerId: number, employeeId: number) {
    return this.http.put(
      `${this.updateManager}/${employeeId}/${managerId}`, null, 
      { headers: this.getAuthHeaders() } 
    ).pipe(
      catchError(this.handleError)
    );
  }
  

  private handleError(error: HttpErrorResponse) {
    let message = '';
    if (error.status === 0) {
      message = "Some error occurred, try again later";
    } else {
      message = `${error.error}`;
    }
    return throwError(() => new Error(message));
  }
}