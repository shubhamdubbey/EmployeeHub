package com.shubham.hr.entity;

import java.util.List;

import com.shubham.hr.utility.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@Column(name = "employee_id")
	private int EmployeeId;

	@OneToMany(cascade= CascadeType.ALL, mappedBy = "id", targetEntity = GradesHistory.class)
	private List<GradesHistory> gradesHisotry;
	
	@Column(name = "first_name")
	private String FirstName;
	
	@Column(name = "last_name")
	private String LastName;
	
	@Pattern(regexp="(^$|[0-9]{10})", message = "invalid phone number")
	@Column(name = "phone_number")
	private String PhoneNumber;
	
	@Column(name = "email_address")
	private String EmailAddress;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Roles role;	
	
	@ManyToOne
	@JoinColumn(name = "current_grade_id")
	private Grades grades;
	
	public int getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public Roles getRoles() {
		return role;
	}
	public void setRoles(Roles role) {
		this.role = role;
	}
	public List<GradesHistory> getGradesHisotry() {
		return gradesHisotry;
	}
	public Grades getGrades() {
		return grades;
	}
	public void setGrades(Grades grades) {
		this.grades = grades;
	}
	public void setGradesHisotry(GradesHistory gradesHistory) {
		this.gradesHisotry.add(gradesHistory);
	}
	
	public void removeGradesHIstory(GradesHistory gradesHistory)
	{
		this.gradesHisotry.remove(gradesHistory.getId());
	}
	
	
}
