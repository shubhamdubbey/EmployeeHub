import { Component, OnInit } from '@angular/core';
import { HumanResourceService } from '../../human-resource.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { Approval, ApprovalStatus } from '../../../models/approvals';

@Component({
  selector: 'app-actions',
  templateUrl: './actions.component.html',
  styleUrls: ['./actions.component.css'],
})
export class ActionsComponent implements OnInit {
  approvals: Approval[] = [];
  currentPage: number = 1;
  pageSize: number = 5;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private humanResourceService: HumanResourceService) {}

  ngOnInit(): void {
    this.fetchApprovals();
  }

  /** 🔹 Fetch approvals list for current user */
  fetchApprovals(): void {
    const userId = Number(sessionStorage.getItem('username'));
    if (!userId) {
      this.errorMessage = 'User not found in session.';
      return;
    }

    this.isLoading = true;
    this.humanResourceService
      .getApprovalsList(userId)
      .pipe(
        catchError((err) => {
          console.error('Error fetching approvals:', err);
          this.errorMessage = 'Failed to load approvals.';
          this.isLoading = false;
          return of([]);
        })
      )
      .subscribe((data: Approval[]) => {
        this.approvals = data || [];
        this.isLoading = false;
      });
  }

  /** 🔹 Approve request */
  approveRequest(approval: Approval): void {
    this.updateStatus(approval.id, ApprovalStatus.APPROVED);
  }

  /** 🔹 Reject request */
  rejectRequest(approval: Approval): void {
    this.updateStatus(approval.id, ApprovalStatus.REJECTED);
  }

  /** 🔹 Common update status call */
  private updateStatus(id: string, status: ApprovalStatus): void {
    this.humanResourceService
      .updateApprovalStatus(id, status)
      .pipe(
        catchError((err) => {
          console.error(`Error updating status for ${id}:`, err);
          this.errorMessage = 'Failed to update approval status.';
          return of(null);
        })
      )
      .subscribe((response) => {
        // Check if response exists and has success message
        if (response && response.message === 'success') {
          this.approvals = this.approvals.filter((a) => a.id !== id);
        } else {
          this.errorMessage = 'Failed to update approval status.';
        }
      });
  }
}