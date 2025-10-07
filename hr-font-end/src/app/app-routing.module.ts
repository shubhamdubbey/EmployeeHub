import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RetrieveEmployeeByIdComponent } from './component/retrieve-employee-by-id/retrieve-employee-by-id.component';
import { RetrieveEmployeesComponent } from './component/retrieve-employees/retrieve-employees.component';
import { UpdateEmployeeGradeComponent } from './component/update-employee-grade/update-employee-grade.component';
import { AddEmployeesComponent } from './component/add-employees/add-employees.component';
import { AuthenticateGuardService } from './authenticate-guard.service';
import { NotFoundComponent } from './not-found/not-found.component';
import { LeaveBalanceComponent } from './component/leave-balance/leave-balance.component';
import { ApplyLeavesComponent } from './component/apply-leaves/apply-leaves.component';
import { LeaveHistoryComponent } from './component/leave-history/leave-history.component';
import { PersonalDetailsComponent } from './component/personal-details/personal-details.component';
import { ActionsComponent } from './component/actions/actions.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthenticateGuardService] },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: "login", pathMatch: "full" },

  { path: 'retrieveEmployeeById', component: RetrieveEmployeeByIdComponent, canActivate:[AuthenticateGuardService], data: { roles: ['ROLE_ADMIN', 'ROLE_HR', 'ROLE_EMPLOYEE'] }},
  { path: 'applyLeaves', component: ApplyLeavesComponent, canActivate:[AuthenticateGuardService], data: { roles: ['ROLE_ADMIN', 'ROLE_HR', 'ROLE_EMPLOYEE'] }},
  { path: 'actions', component: ActionsComponent, canActivate:[AuthenticateGuardService], data: { roles: ['ROLE_ADMIN', 'ROLE_HR'] }},
  { path: 'personalDetails', component: PersonalDetailsComponent, canActivate:[AuthenticateGuardService], data: { roles: ['ROLE_ADMIN', 'ROLE_HR', 'ROLE_EMPLOYEE'] }},
  { path: 'leaveHistory', component: LeaveHistoryComponent, canActivate:[AuthenticateGuardService], data: { roles: ['ROLE_ADMIN', 'ROLE_HR', 'ROLE_EMPLOYEE'] }},
  { path: 'leaveBalance', component: LeaveBalanceComponent, canActivate:[AuthenticateGuardService], data: { roles: ['ROLE_ADMIN', 'ROLE_HR', 'ROLE_EMPLOYEE'] }},
  { path: 'retrieveEmployees', component: RetrieveEmployeesComponent, canActivate:[AuthenticateGuardService], data: { roles: ['ROLE_ADMIN', 'ROLE_HR'] }},
  { path: 'addEmployee', component: AddEmployeesComponent, canActivate:[AuthenticateGuardService], data: { roles: ['ROLE_ADMIN', 'ROLE_HR'] }},
  { path: 'updateEmployeeGrade/:empId', component: UpdateEmployeeGradeComponent, canActivate:[AuthenticateGuardService], data: { roles: ['ROLE_ADMIN', 'ROLE_HR'] }},

  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
