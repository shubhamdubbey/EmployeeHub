package com.cts.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.hr.HrApplication;
import com.cts.hr.controller.HrController;


@SpringBootTest(classes=HrApplication.class)
public class HrApplicationTest {
	
	@Autowired
	private HrController hrController;
	
   @Test
	public void contextLoads() {
	   assertNotNull(hrController);
	}
}

