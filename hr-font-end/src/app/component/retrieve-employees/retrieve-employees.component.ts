import { Component, OnInit } from '@angular/core';
import { Users } from '../../../models/users';
import { HumanResourceService } from '../../human-resource.service';
import { PaginatedResponse } from '../../../models/paginatedresponse';

@Component({
  selector: 'app-retrieve-employees',
  templateUrl: './retrieve-employees.component.html',
  styleUrls: ['./retrieve-employees.component.css']
})
export class RetrieveEmployeesComponent implements OnInit {
  users: Users[] = [];
  totalPages: number = 0;
  pageNumbers: number[] = [];
  currentPage: number = 0;  // spring is 0-based
  pageSize: number = 3;     // match backend default
  totalElements: number = 0;
  isLoading: boolean = false;

  constructor(private humanResourceService: HumanResourceService) {}

  ngOnInit(): void {
    this.loadEmployees(this.currentPage);
  }

  loadEmployees(pageNumber: number): void {
    this.humanResourceService.getEmployees(pageNumber, this.pageSize)
      .subscribe((response: PaginatedResponse<Users>) => {
        this.users = response.content;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements || 0;
        this.currentPage = response.number;
        this.pageNumbers = this.getVisiblePageNumbers();
      });
  }

  onPageClick(pageNumber: number): void {
    if (pageNumber >= 0 && pageNumber < this.totalPages) {
      this.loadEmployees(pageNumber);
    }
  }

  goToFirstPage(): void {
    if (this.currentPage !== 0) {
      this.loadEmployees(0);
    }
  }

  goToLastPage(): void {
    if (this.currentPage !== this.totalPages - 1) {
      this.loadEmployees(this.totalPages - 1);
    }
  }

  goToPreviousPage(): void {
    if (this.hasPreviousPage()) {
      this.loadEmployees(this.currentPage - 1);
    }
  }

  goToNextPage(): void {
    if (this.hasNextPage()) {
      this.loadEmployees(this.currentPage + 1);
    }
  }

  hasPreviousPage(): boolean {
    return this.currentPage > 0;
  }

  hasNextPage(): boolean {
    return this.currentPage < this.totalPages - 1;
  }

  // Smart pagination: show max 5 page numbers with current page in middle when possible
  getVisiblePageNumbers(): number[] {
    const maxVisible = 5;
    
    if (this.totalPages <= maxVisible) {
      return Array.from({ length: this.totalPages }, (_, i) => i);
    }

    const half = Math.floor(maxVisible / 2);
    let start = Math.max(0, this.currentPage - half);
    let end = Math.min(this.totalPages, start + maxVisible);

    // Adjust start if we're near the end
    if (end === this.totalPages) {
      start = Math.max(0, end - maxVisible);
    }

    return Array.from({ length: end - start }, (_, i) => start + i);
  }

  // Helper to show page range info
  getPageRangeText(): string {
    const start = this.currentPage * this.pageSize + 1;
    const end = Math.min((this.currentPage + 1) * this.pageSize, this.totalElements);
    return `${start}-${end} of ${this.totalElements}`;
  }
}