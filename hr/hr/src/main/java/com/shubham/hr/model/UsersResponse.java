package com.shubham.hr.model;

import java.util.List;
import java.util.Objects;

import com.shubham.hr.entity.Grades;
import com.shubham.hr.entity.GradesHistory;
import com.shubham.hr.utility.Roles;

public class UsersResponse {

	private int EmployeeId;
	private List<GradesHistory> gradesHisotry;
	private String FirstName;
	private String LastName;
	private String PhoneNumber;
	private String EmailAddress;
	private Roles roles;
	private Grades grades;
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
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public Grades getGrades() {
		return grades;
	}
	public void setGrades(Grades grades) {
		this.grades = grades;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersResponse other = (UsersResponse) obj;
		return Objects.equals(EmailAddress, other.EmailAddress) && EmployeeId == other.EmployeeId
				&& Objects.equals(FirstName, other.FirstName) && Objects.equals(LastName, other.LastName)
				&& Objects.equals(PhoneNumber, other.PhoneNumber) && Objects.equals(grades, other.grades)
				&& Objects.equals(gradesHisotry, other.gradesHisotry) && roles == other.roles;
	}
	@Override
	public String toString() {
		return "UsersResponse [EmployeeId=" + EmployeeId + ", gradesHisotry=" + gradesHisotry + ", FirstName="
				+ FirstName + ", LastName=" + LastName + ", PhoneNumber=" + PhoneNumber + ", EmailAddress="
				+ EmailAddress + ", role=" + roles + ", grades=" + grades + "]";
	}
	
	
	
}
