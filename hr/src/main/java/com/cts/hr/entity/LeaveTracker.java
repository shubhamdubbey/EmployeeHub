package com.cts.hr.entity;

import com.cts.hr.utility.LeaveType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Collate;

import java.time.LocalDate;

@Entity
@Table(name = "LeaveTracker")
public class LeaveTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "leave_type")
    private LeaveType leaveType;

    @Column(name = "from")
    private LocalDate  from;

    @Column(name = "to")
    private LocalDate  to;

    @Column(name = "reason")
    private String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
