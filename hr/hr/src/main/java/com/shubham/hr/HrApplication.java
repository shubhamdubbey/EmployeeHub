package com.shubham.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.shubham.hr.entity.Grades;
import com.shubham.hr.repository.GradesRepository;
import com.shubham.hr.service.HrService;
import com.shubham.hr.service.HrServiceImpl;


@SpringBootApplication(scanBasePackages = "com.shubham.hr.*")
@EntityScan(basePackages = "com.shubham.hr.entity")
@EnableJpaRepositories(basePackages = "com.shubham.hr.repository")

public class HrApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
		System.out.println("hello the legend");

	}
}
