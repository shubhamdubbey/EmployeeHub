export enum ApprovalStatus {
    PENDING = 'PENDING',
    APPROVED = 'APPROVED',
    REJECTED = 'REJECTED'
  }
  
  export enum ApprovalType {
    HOEM_MANAGER = 'HOME MANAGER',
    LEAVE = "Leave"
  }
  
  export interface Approval {
    id: string;
    employeeId: number;
    request: string;
    status: ApprovalStatus;
    approvalType: ApprovalType;
    approverId: number;
  }