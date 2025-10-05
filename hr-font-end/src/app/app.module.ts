import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DeleteEmployeeComponent } from './component/delete-employee/delete-employee.component';
import { RetrieveEmployeesComponent } from './component/retrieve-employees/retrieve-employees.component';
import { UpdateEmployeeGradeComponent } from './component/update-employee-grade/update-employee-grade.component';
import { RetrieveEmployeeByIdComponent } from './component/retrieve-employee-by-id/retrieve-employee-by-id.component';
import { HttpClientModule } from '@angular/common/http';
import { AddEmployeesComponent } from './component/add-employees/add-employees.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { LeaveBalanceComponent } from './component/leave-balance/leave-balance.component';
import { ApplyLeavesComponent } from './component/apply-leaves/apply-leaves.component';
import { LeaveHistoryComponent } from './component/leave-history/leave-history.component';
import { PersonalDetailsComponent } from './component/personal-details/personal-details.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    DeleteEmployeeComponent,
    RetrieveEmployeesComponent,
    UpdateEmployeeGradeComponent,
    RetrieveEmployeeByIdComponent,
    AddEmployeesComponent,
    NotFoundComponent,
    LeaveBalanceComponent,
    ApplyLeavesComponent,
    LeaveHistoryComponent,
    PersonalDetailsComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 
}