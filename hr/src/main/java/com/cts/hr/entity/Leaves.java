package com.cts.hr.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "leaves")
@Data
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

}
