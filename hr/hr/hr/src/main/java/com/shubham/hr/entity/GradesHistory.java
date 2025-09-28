package com.shubham.hr.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gradeshistory")
public class GradesHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id")
	private int id;
	@Column(name = "assigned_on")
	private LocalDate assignedon;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name = "grade_id")
	private Grades grades;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getAssignedon() {
		return assignedon;
	}
	public void setAssignedon(LocalDate assignedon) {
		this.assignedon = assignedon;
	}
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Grades getGrades() {
		return grades;
	}
	public void setGrades(Grades grades) {
		this.grades = grades;
	}
	

	
	
}
