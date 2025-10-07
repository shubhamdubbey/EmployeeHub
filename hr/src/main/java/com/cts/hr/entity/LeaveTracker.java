package com.cts.hr.entity;

import com.cts.hr.utility.ApprovalStatus;
import com.cts.hr.utility.LeaveType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_tracker")
public class LeaveTracker {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "leave_type")
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(name = "from_date")
    private LocalDate  from;

    @Column(name = "to_date")
    private LocalDate  to;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }
}
