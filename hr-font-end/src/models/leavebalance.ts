export class LeaveBalance {
    employeeId: number;
    sickLeave: number;
    casualLeave: number;
    earnedLeave: number;
    paternityLeave: number;
  
    constructor(
      employeeId: number = 0,
      sickLeave: number = 0,
      casualLeave: number = 0,
      earnedLeave: number = 0,
      paternityLeave: number = 0
    ) {
      this.employeeId = employeeId;
      this.sickLeave = sickLeave;
      this.casualLeave = casualLeave;
      this.earnedLeave = earnedLeave;
      this.paternityLeave = paternityLeave;
    }
  }
  