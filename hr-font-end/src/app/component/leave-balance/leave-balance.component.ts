import { Component, OnInit } from '@angular/core';
import { HumanResourceService } from '../../human-resource.service';
import { LeaveBalance } from '../../../models/leavebalance';

@Component({
  selector: 'app-leave-balance',
  templateUrl: './leave-balance.component.html',
  styleUrls: ['./leave-balance.component.css']
})
export class LeaveBalanceComponent implements OnInit {

  leaveBalance: LeaveBalance | null = null;
  errorMessage: string = '';

  constructor(private humanResourceService: HumanResourceService) {}

  ngOnInit(): void {
    const employeeId = sessionStorage.getItem('username');
    if (employeeId) {
      const empId = Number(employeeId);
      if (!isNaN(empId)) {
        this.loadLeaveBalance(empId);
      } else {
        this.errorMessage = 'Employee ID is not a valid number!';
      }
    } else {
      this.errorMessage = 'Employee ID not found!';
    }
  }

  loadLeaveBalance(empId: number) {
    this.humanResourceService.getLeaveBalances(empId).subscribe({
      next: (data: LeaveBalance) => {
        this.leaveBalance = data;
      },
      error: (error) => {
        this.errorMessage = 'Failed to fetch leave balance!';
        console.error(error);
      }
    });
  }
}