package com.shubham.hr.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "grades")
public class Grades {

	@Id
	@Column(name = "id")
	private int identification;
	
	
	@OneToMany(cascade =  CascadeType.ALL, mappedBy = "EmployeeId", targetEntity = Users.class)
	private List<Users> users;
	
	@OneToMany(cascade =  CascadeType.ALL, mappedBy = "id", targetEntity = GradesHistory.class)
	private List<GradesHistory> gradeshistory;
	

	@Column(name = "name")
	private String fullName;
	
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	public List<GradesHistory> getGradeshistory() {
		return gradeshistory;
	}
	public void setGradeshistory(List<GradesHistory> gradeshistory) {
		this.gradeshistory = gradeshistory;
	}

	
	public int getIdentification() {
		return identification;
	}
	public void setIdentification(int identification) {
		this.identification = identification;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	
}
