package com.shubham.hr.model;

import java.util.Objects;
import com.shubham.hr.utility.Roles;

import jakarta.validation.constraints.Size;

public class UsersRequest {

	@Size(min = 6, max = 6)
	private int EmployeeId;
	private String FirstName;
	private String LastName;
	private String PhoneNumber;
	private String EmailAddress;
	private Roles role;
	private int grade_id;
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
		EmailAddress = emailAddress + "@cognizant.com";
	}
	public Roles getRoles() {
		return role;
	}
	public void setRoles(Roles role) {
		this.role = role;
	}
	
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(EmailAddress, EmployeeId, FirstName, LastName, PhoneNumber, grade_id, role);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersRequest other = (UsersRequest) obj;
		return Objects.equals(EmailAddress, other.EmailAddress) && EmployeeId == other.EmployeeId
				&& Objects.equals(FirstName, other.FirstName) && Objects.equals(LastName, other.LastName)
				&& Objects.equals(PhoneNumber, other.PhoneNumber) && grade_id == other.grade_id && role == other.role;
	}
	@Override
	public String toString() {
		return "UsersRequest [EmployeeId=" + EmployeeId + ", FirstName=" + FirstName + ", LastName=" + LastName
				+ ", PhoneNumber=" + PhoneNumber + ", EmailAddress=" + EmailAddress + ", role=" + role + ", grade_id="
				+ grade_id + "]";
	}
	
	
	
}
