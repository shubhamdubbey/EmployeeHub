import { Component, OnInit } from '@angular/core';
import { HumanResourceService } from '../../human-resource.service';
import { Users } from '../../../models/users';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-personal-details',
  templateUrl: './personal-details.component.html',
  styleUrls: ['./personal-details.component.css']
})
export class PersonalDetailsComponent implements OnInit {

  user: Users = new Users();
  loading: boolean = false;
  editMode: boolean = false;
  updateSuccess: boolean = false;
  errorMessage: string = '';
  submitted: boolean = false;
  noRecordFound: boolean = false;

  personalDetailsForm!: FormGroup;

  constructor(private humanResourceService: HumanResourceService) {}

  ngOnInit(): void {
    this.personalDetailsForm = new FormGroup({
      phoneNumber: new FormControl({ value: '', disabled: true }, [
        Validators.required,
        Validators.pattern(/^\d{10}$/)
      ]),
      emailId: new FormControl({ value: '', disabled: true }, [
        Validators.required,
        Validators.email
      ])
    });

    this.getEmployeeDetails();
  }

  getEmployeeDetails(): void {
    const empIdStr = sessionStorage.getItem('username');
    if (!empIdStr) {
      this.errorMessage = 'Employee ID not found!';
      return;
    }

    const empId = Number(empIdStr);
    if (isNaN(empId)) {
      this.errorMessage = 'Employee ID is invalid!';
      return;
    }

    // Create a Users object with just employeeId
    const userReq = new Users();
    userReq.employeeId = empId;

    this.loading = true;
    this.humanResourceService.getEmployeeById(userReq).subscribe({
      next: (data) => {
        this.user = data;
        this.personalDetailsForm.patchValue({
          phoneNumber: this.user.phoneNumber,
          emailId: this.user.emailAddress
        });
        this.submitted = true;
        this.noRecordFound = false;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = 'Failed to fetch details. Please try again later.';
        this.noRecordFound = true;
        console.error('Error fetching employee details:', err);
      }
    });
  }

  toggleEdit(): void {
    if (this.editMode) {
      this.confirmUpdate();
    } else {
      this.editMode = true;
      this.personalDetailsForm.get('phoneNumber')?.enable();
      this.personalDetailsForm.get('emailId')?.enable();
    }
  }

  confirmUpdate(): void {
    if (this.personalDetailsForm.invalid) {
      this.personalDetailsForm.markAllAsTouched();
      return;
    }

    this.user.phoneNumber = this.personalDetailsForm.value['phoneNumber'];
    this.user.emailAddress = this.personalDetailsForm.value['emailId'];

    this.loading = true;
    this.humanResourceService.updateEmployee(this.user, this.user.grade_id).subscribe({
      next: () => {
        this.loading = false;
        this.editMode = false;
        this.updateSuccess = true;
        this.personalDetailsForm.get('phoneNumber')?.disable();
        this.personalDetailsForm.get('emailId')?.disable();
        setTimeout(() => this.updateSuccess = false, 3000);
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = 'Failed to update. Please try again.';
        console.error('Error updating employee:', err);
      }
    });
  }
}
