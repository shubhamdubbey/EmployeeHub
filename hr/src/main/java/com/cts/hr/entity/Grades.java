package com.cts.hr.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "grades")

public class Grades {

	@Id
	@Column(name = "id")
	private int identification;
	
	@Column(name = "name")
	private String fullName;
	
	@OneToMany(mappedBy = "EmployeeId", targetEntity = Users.class)
	private List<Users> users;
	
	@OneToMany(mappedBy = "id", targetEntity = GradesHistory.class)
	private List<GradesHistory> gradeshistory;
	
}
