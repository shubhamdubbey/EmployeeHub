import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HumanResourceService } from '../../human-resource.service';
import { ApplyLeaveRequest } from '../../../models/applyleavesrequest';
import { ApplyLeaveResponse } from '../../../models/applyleavseresponse';

@Component({
  selector: 'app-apply-leaves',
  templateUrl: './apply-leaves.component.html',
  styleUrls: ['./apply-leaves.component.css']
})
export class ApplyLeavesComponent {

  leaveForm = new FormGroup({
    fromDate: new FormControl('', Validators.required),
    toDate: new FormControl('', Validators.required),
    leaveType: new FormControl('', Validators.required),
    reason: new FormControl('', Validators.required)
  });

  successMessage: string = '';
  errorMessage: string = '';

  // Leave types mapped to backend enum values
  leaveTypes = [
    { key: 'SICK_LEAVE', label: 'Sick Leave' },
    { key: 'CASUAL_LEAVE', label: 'Casual Leave' },
    { key: 'EARNED_LEAVE', label: 'Earned Leave' },
    { key: 'PATERNITY_LEAVE', label: 'Paternity Leave' }
  ];

  constructor(private humanResourceService: HumanResourceService) {}

  onSubmit() {
    if (this.leaveForm.valid) {
      const employeeId = sessionStorage.getItem('username');
      if (!employeeId) {
        this.errorMessage = "Employee ID not found in session!";
        return;
      }

      const leaveRequest: ApplyLeaveRequest = {
        employeeId: Number(employeeId),
        leaveType: this.leaveForm.value.leaveType as string,
        fromDate: this.leaveForm.value.fromDate as string,
        toDate: this.leaveForm.value.toDate as string,
        reason: this.leaveForm.value.reason as string
      };

      this.humanResourceService.applyLeave(leaveRequest).subscribe({
        next: (response: ApplyLeaveResponse) => {
          if (response.success) {
            this.successMessage = response.message || "Leave applied successfully!";
            this.errorMessage = '';
            this.leaveForm.reset();
          } else {
            this.errorMessage = response.message || "Failed to apply leave!";
            this.successMessage = '';
          }
        },
        error: (err) => {
          this.errorMessage = "Error occurred while applying leave!";
          this.successMessage = '';
          console.error(err);
        }
      });
    }
  }

  onCancel() {
    this.leaveForm.reset();
    this.successMessage = '';
    this.errorMessage = '';
  }
}
