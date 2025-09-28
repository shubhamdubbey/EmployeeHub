package com.cts.hr.entity;

import java.util.List;

import com.cts.hr.utility.Roles;

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
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
public class Users {
	
	@Id
	@Column(name = "employee_id")
	private int EmployeeId;

	@OneToMany(mappedBy = "id", targetEntity = GradesHistory.class)
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

	public void setRoles(Roles roles) {
		this.role = roles;
	}
	
	public Roles getRoles() {
		return this.role;
	}
	
	
}
