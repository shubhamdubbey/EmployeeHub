package com.shubham.hr.model;

import java.util.Objects;

public class GradesDTO {

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
	@Override
	public String toString() {
		return "GradesResponse [identification=" + identification + ", fullName=" + fullName + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(fullName, identification);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GradesDTO other = (GradesDTO) obj;
		return Objects.equals(fullName, other.fullName) && identification == other.identification;
	}
	
}
