package com.cts.hr.entity;

import com.cts.hr.utility.LeaveType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Collate;

import java.time.LocalDate;

@Entity
@Table(name = "LeaveTracker")
@Data
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
}
