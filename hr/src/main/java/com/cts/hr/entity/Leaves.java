package com.cts.hr.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "leaves")
public class Leaves {

    @Id
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "sickLeave")
    private int sickLeave;

    @Column(name = "casualLeave")
    private int casualLeave;

    @Column(name = "earnedLeave")
    private int earnedLeave;

    @Column(name = "paternityLeave")
    private int paternityLeave;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(int sickLeave) {
        this.sickLeave = sickLeave;
    }

    public int getCasualLeave() {
        return casualLeave;
    }

    public void setCasualLeave(int casualLeave) {
        this.casualLeave = casualLeave;
    }

    public int getEarnedLeave() {
        return earnedLeave;
    }

    public void setEarnedLeave(int earnedLeave) {
        this.earnedLeave = earnedLeave;
    }

    public int getPaternityLeave() {
        return paternityLeave;
    }

    public void setPaternityLeave(int paternityLeave) {
        this.paternityLeave = paternityLeave;
    }
}
