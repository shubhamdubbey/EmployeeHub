package com.cts.hr.entity;

import com.cts.hr.utility.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@Column(name = "employee_id")
	private int EmployeeId;

    @OneToMany(mappedBy = "id", targetEntity = GradesHistory.class)
	private List<GradesHistory> gradesHisotry;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Pattern(regexp="(^$|[0-9]{10})", message = "invalid phone number")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email_address")
	private String emailAddress;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Roles role;	
	
	@ManyToOne
	@JoinColumn(name = "current_grade_id")
	private Grades grades;

    @ManyToOne(targetEntity = HomeManager.class)
    @JoinColumn(name = "employee_id")
    private HomeManager homeManager;

	public void setRoles(Roles roles) {
		this.role = roles;
	}
	
	public Roles getRoles() {
		return this.role;
	}

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public List<GradesHistory> getGradesHisotry() {
        return gradesHisotry;
    }

    public void setGradesHisotry(List<GradesHistory> gradesHisotry) {
        this.gradesHisotry = gradesHisotry;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Grades getGrades() {
        return grades;
    }

    public void setGrades(Grades grades) {
        this.grades = grades;
    }
}
