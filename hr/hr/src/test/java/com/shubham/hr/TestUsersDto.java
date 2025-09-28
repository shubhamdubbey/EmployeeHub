package com.shubham.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.shubham.hr.model.UsersDTO;
import com.shubham.hr.utility.Roles;

public class TestUsersDto {

	@Test
	public void testUsersDto() {
		UsersDTO usersDto = new UsersDTO();
		usersDto.setEmployeeId(1001);
		usersDto.setEmailAddress("shubham" + "@cognizant.com");
		usersDto.setFirstName("shubham");
		usersDto.setLastName("shubham");
		usersDto.setPhoneNumber("9755033217");
		usersDto.setRoles(Roles.HR);
		usersDto.setGrade_id(1);
		
		assertEquals(1001, usersDto.getEmployeeId());
		assertEquals("shubham", usersDto.getFirstName());
		assertEquals("shubham", usersDto.getLastName());
		assertEquals("shubham@cognizant.com", usersDto.getEmailAddress());
		assertEquals("9755033217", usersDto.getPhoneNumber());
		assertEquals(1,usersDto.getGrade_id());
		assertEquals(Roles.HR, usersDto.getRoles());
		
	}
}
