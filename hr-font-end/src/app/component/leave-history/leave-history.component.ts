import { Component, OnInit } from '@angular/core';
import { HumanResourceService } from '../../human-resource.service';
import { ApplyLeaveRequest } from '../../../models/applyleavesrequest';

@Component({
  selector: 'app-leave-history',
  templateUrl: './leave-history.component.html',
  styleUrl: './leave-history.component.css'
})
export class LeaveHistoryComponent implements OnInit {

  leaveHistory!: ApplyLeaveRequest[];
  pageSize: number = 5;
  currentPage: number = 1;
  errorMessage: string = '';

  constructor(private humanResourceService: HumanResourceService) {}

  ngOnInit(): void {
    const employeeId = sessionStorage.getItem('username');

    if (employeeId) {
      const empId = Number(employeeId);

      if (isNaN(empId)) {
        this.errorMessage = 'Invalid employee ID in session!';
        console.error('Invalid employee ID in sessionStorage:', employeeId);
        return;
      }

      this.humanResourceService.getLeavesHistory(empId).subscribe({
        next: (data) => {
          this.leaveHistory = data;
        },
        error: (err) => {
          console.error('Error fetching leave history:', err);
          this.errorMessage = 'Failed to load leave history!';
        }
      });

    } else {
      this.errorMessage = 'Employee ID not found!';
      console.error('Employee ID not found in sessionStorage');
    }
  }
}