import { Component } from '@angular/core';
import { Users } from '../../../models/users';
import { HumanResourceService } from '../../human-resource.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticateUsersService } from '../../authenticate-users.service';

@Component({
  selector: 'app-retrieve-employee-by-id',
  templateUrl: './retrieve-employee-by-id.component.html',
  styleUrls: ['./retrieve-employee-by-id.component.css']
})
export class RetrieveEmployeeByIdComponent {
  user!: Users;
  users: Users = new Users();
  submitted = false;
  noRecordFound = false;
  empId: number;
  reset = false;
  alertMessage = "";

  idForm = new FormGroup({
    id: new FormControl('', [
      Validators.required,
      Validators.pattern('^[0-9]{6}$'),
      Validators.minLength(6),
      Validators.maxLength(6)
    ])
  });

  constructor(
    private router: Router,
    private humanResourceService: HumanResourceService,
    public autheticateUsersService: AuthenticateUsersService
  ) {}

  /** Only ADMIN or HR can delete employees */
  deleteEmployee(id: number) {
    if (!this.autheticateUsersService.hasRole(['ROLE_ADMIN', 'ROLE_HR'])) {
      this.alertMessage = "You don't have enough privileges to delete employees.";
      return;
    }

    this.humanResourceService.deleteEmployee(id).subscribe({
      next: (data) => {
        console.log("Employee deleted successfully", data);
        this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
          this.router.navigate(['retrieveEmployeeById']);
        });
      },
      error: (err) => {
        console.error("Error deleting employee", err);
      }
    });
  }

  /** Allow search for all roles, but filter result accordingly */
  search() {
    if (!this.autheticateUsersService.isUserLoggedIn()) {
      this.alertMessage = "Please log in to search employees.";
      return;
    }

    this.humanResourceService.getEmployeeById(this.users).subscribe({
      next: (data) => {
        this.user = data;
        this.submitted = true;
        this.noRecordFound = false;
        this.alertMessage = "";
      },
      error: () => {
        this.noRecordFound = true;
        this.submitted = true;
        console.log("No employee found");
      }
    });
  }

  ngOnInit() {}

  onReset() {
    this.reset = true;
    this.submitted = false;
    this.noRecordFound = false;
    this.user = null!;
    this.alertMessage = "";
  }

  onSubmit() {
    this.reset = false;
    this.users.employeeId = +this.idForm.controls['id'].value;
    this.search();
  }

  /** Utility to check role from template */
  hasPrivilege(roles: string[]): boolean {
    return this.autheticateUsersService.hasRole(roles);
  }
}
