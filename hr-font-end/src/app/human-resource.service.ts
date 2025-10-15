import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

import { Users } from '../models/users';
import { Grades } from '../models/grade';
import { LeaveBalance } from '../models/leavebalance';
import { ApplyLeaveRequest } from '../models/applyleavesrequest';
import { Approval, ApprovalStatus } from '../models/approvals';
import { PaginatedResponse } from '../models/paginatedresponse';
import { PasswordChangeRequest } from '../models/passwordchangerequest';

@Injectable({
  providedIn: 'root'
})
export class HumanResourceService {

  empId: number;
  private baseUrl = "http://localhost:8090/api";

  private retrieveEmployeesURL = `${this.baseUrl}/getEmployees`;
  private deleteEmployeeURL = `${this.baseUrl}/deleteEmployee`;
  private registerEmployeeURL = `${this.baseUrl}/addEmployee`;
  private retirieveEmployeeByIdURL = `${this.baseUrl}/getEmployeeById`;
  private updateEmployeesGradeURL = `${this.baseUrl}/updateEmployeesGrade`;
  private retrieveGradesURL = `${this.baseUrl}/grades`;
  private checkLeaveBalance = `${this.baseUrl}/getLeaves`;
  private applyLeaves = `${this.baseUrl}/applyLeaves`;
  private getHistoricalLeave = `${this.baseUrl}/getLeavesHistory`;
  private updateManager = `${this.baseUrl}/updateManager`;
  private listOfApprovalsPending = `${this.baseUrl}/listOfApproval`;
  private updateApproval = `${this.baseUrl}/updateApproval`;
  private changePasswordURL = `${this.baseUrl}/changePassword`;

  constructor(private http: HttpClient) { }

  // JWT Auth Header
  private getAuthHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? token : ''
    });
  }

  public getEmployees(pageNumber: number, pageSize: number): Observable<PaginatedResponse<Users>> {
    const url = `${this.retrieveEmployeesURL}?page=${pageNumber}&size=${pageSize}`;
    return this.http.get<PaginatedResponse<Users>>(url, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public getEmployeeById(users: Users): Observable<Users> {
    this.empId = users.employeeId;
    return this.http.get<Users>(`${this.retirieveEmployeeByIdURL}/${this.empId}`, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.deleteEmployeeURL}/${id}`, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public getGrades(): Observable<Grades[]> {
    return this.http.get<Grades[]>(this.retrieveGradesURL, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public registerEmployee(users: Users): Observable<any> {
    return this.http.post(this.registerEmployeeURL, users, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public updateEmployee(users: Users, gradeId: number): Observable<any> {
    this.empId = users.employeeId;
    return this.http.put(`${this.updateEmployeesGradeURL}/${this.empId}/${gradeId}`, users, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public getLeaveBalances(id: number): Observable<LeaveBalance> {
    return this.http.get<LeaveBalance>(`${this.checkLeaveBalance}/${id}`, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public applyLeave(applyLeaveRequest: ApplyLeaveRequest): Observable<any> {
    return this.http.post(this.applyLeaves, applyLeaveRequest, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public getLeavesHistory(id: number): Observable<ApplyLeaveRequest[]> {
    return this.http.get<ApplyLeaveRequest[]>(`${this.getHistoricalLeave}/${id}`, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public updateHomeManager(managerId: number, employeeId: number): Observable<any> {
    return this.http.put(`${this.updateManager}/${employeeId}/${managerId}`, null, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public getApprovalsList(id: number): Observable<Approval[]> {
    return this.http.get<Approval[]>(`${this.listOfApprovalsPending}/${id}`, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public updateApprovalStatus(id: string, status: ApprovalStatus): Observable<any> {
    return this.http.put<any>(`${this.updateApproval}/${id}/${status}`, null, { headers: this.getAuthHeaders() })
      .pipe(catchError(this.handleError));
  }

  public changePassword(dto: PasswordChangeRequest, token: string | null): Observable<any> {
    return this.http.post(this.changePasswordURL, dto, { headers: this.getAuthHeaders()  })
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    let message = '';
    if (error.status === 0) {
      message = "Some error occurred, try again later.";
    } else {
      message = `${error.error}`;
    }
    return throwError(() => new Error(message));
  }
}