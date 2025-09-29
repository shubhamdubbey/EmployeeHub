package com.cts.hr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "HomeManager")
@Getter
@Setter
public class HomeManager {

    @Id
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "name")
    private int name;

    @Column(name = "designation")
    private int designation;

    @Column(name = "reportees")
    @OneToMany(mappedBy = "employee_id", targetEntity = Users.class)
    private List<Users> reportees;
}
