package com.cts.hr.entity;

import com.cts.hr.utility.ApprovalStatus;
import com.cts.hr.utility.ApprovalType;
import jakarta.persistence.*;

@Entity
@Table(name = "approvals")
public class Approvals {

    @Id
    @Column(name = "approval_id")
    private String id;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "request")
    private String request;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @Column(name = "approval_type", columnDefinition = "varchar(50)")
    @Enumerated(EnumType.STRING)
    private ApprovalType approvalType;

    @Column(name = "approver_id", columnDefinition = "varchar(50)")
    private int approverId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApprovalType getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(ApprovalType approvalType) {
        this.approvalType = approvalType;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }
}
