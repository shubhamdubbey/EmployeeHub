import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { DeleteEmployeeComponent } from './component/delete-employee/delete-employee.component';
import { RetrieveEmployeeByIdComponent } from './component/retrieve-employee-by-id/retrieve-employee-by-id.component';
import { RetrieveEmployeesComponent } from './component/retrieve-employees/retrieve-employees.component';
import { UpdateEmployeeGradeComponent } from './component/update-employee-grade/update-employee-grade.component';
import { AddEmployeesComponent } from './component/add-employees/add-employees.component';
import { AuthenticateGuardService } from './authenticate-guard.service';
import { NotFoundComponent } from './not-found/not-found.component';

const routes: Routes = [
  { path : 'home',component : HomeComponent, canActivate:[AuthenticateGuardService] },
  { path : 'login',component : LoginComponent },
  { path : '', redirectTo : "login", pathMatch : "full" },
  { path: 'retrieveEmployeeById', component: RetrieveEmployeeByIdComponent, canActivate:[AuthenticateGuardService] },
  { path: 'retrieveEmployees', component: RetrieveEmployeesComponent, canActivate:[AuthenticateGuardService] },
  { path: 'addEmployee', component: AddEmployeesComponent, canActivate:[AuthenticateGuardService] },
  { path: 'updateEmployeeGrade/:empId', component: UpdateEmployeeGradeComponent , canActivate:[AuthenticateGuardService]},
  { path: 'updateEmployeeGrade/:empId', component: UpdateEmployeeGradeComponent , canActivate:[AuthenticateGuardService]},
  { path : '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
