package com.cts.hr.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "grades")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
}
