package com.cts.hr.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class GradesDTO implements Serializable {

	
	private int identification;
	private String fullName;

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

    public GradesDTO() {
    }

    public GradesDTO(int identification, String fullName) {
        this.identification = identification;
        this.fullName = fullName;
    }
}