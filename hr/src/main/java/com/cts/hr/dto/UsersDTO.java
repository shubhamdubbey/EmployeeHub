package com.cts.hr.dto;

import com.cts.hr.utility.Roles;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class UsersDTO implements Serializable {

	private int employeeId;
	private String firstName;
	private String lastName;
	@Pattern(regexp="(^$|[0-9]{10})", message = "invalid phone number")
	private String phoneNumber;
	private String emailAddress;
	private Roles role;
	private int grade_id;
    private int managerId;
	
	public void setRoles(Roles role) {
		// TODO Auto-generated method stub
		this.role = role;
	}

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public Roles getRoles()
	{
		return this.role;
	}

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public UsersDTO(int employeeId, String firstName, String lastName, String phoneNumber, String emailAddress, Roles role, int grade_id, int managerId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.role = role;
        this.grade_id = grade_id;
        this.managerId = managerId;
    }

    public UsersDTO() {
    }
}
