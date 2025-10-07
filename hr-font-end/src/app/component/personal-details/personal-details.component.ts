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
  loading = false;
  editMode = false;
  editManagerMode = false; // 🌟 NEW
  updateSuccess = false;
  successMessage = ''; // 🌟 UPDATED
  errorMessage = '';
  submitted = false;
  noRecordFound = false;
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
      ]),
      managerId: new FormControl({ value: '', disabled: true }) // 🌟 NEW FIELD
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

    const userReq = new Users();
    userReq.employeeId = empId;
    this.loading = true;

    this.humanResourceService.getEmployeeById(userReq).subscribe({
      next: (data) => {
        this.user = data;
        this.personalDetailsForm.patchValue({
          phoneNumber: this.user.phoneNumber,
          emailId: this.user.emailAddress,
          managerId: this.user.managerId
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
        this.successMessage = 'Details updated successfully!';
        this.personalDetailsForm.get('phoneNumber')?.disable();
        this.personalDetailsForm.get('emailId')?.disable();
        setTimeout(() => (this.updateSuccess = false), 3000);
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = 'Failed to update. Please try again.';
        console.error('Error updating employee:', err);
      }
    });
  }

  // 🌟 NEW: Toggle and Update Manager
  toggleManagerEdit(): void {
    if (this.editManagerMode) {
      this.updateManager();
    } else {
      this.editManagerMode = true;
      this.personalDetailsForm.get('managerId')?.enable();
    }
  }

  updateManager(): void {
    const newManagerId = this.personalDetailsForm.value['managerId'];
    if (!newManagerId || isNaN(newManagerId)) {
      this.errorMessage = 'Please enter a valid Manager ID.';
      return;
    }

    this.loading = true;
    this.humanResourceService
      .updateHomeManager(newManagerId, this.user.employeeId)
      .subscribe({
        next: (response: any) => {
          this.loading = false;
          this.editManagerMode = false;
          this.personalDetailsForm.get('managerId')?.disable();
          this.user.managerId = newManagerId;
          this.updateSuccess = true;
          this.successMessage = 'Request has been raised. Once it is approved, the home manager will be updated!';
          setTimeout(() => (this.updateSuccess = false), 3000);
        },
        error: (err) => {
          this.loading = false;
          this.errorMessage = 'Failed to update Home Manager. Please try again.';
          console.error('Error updating home manager:', err);
        }
      });
  }
}
