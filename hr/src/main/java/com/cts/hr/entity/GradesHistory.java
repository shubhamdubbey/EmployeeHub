package com.cts.hr.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gradeshistory")
@Getter
@Setter
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
}
