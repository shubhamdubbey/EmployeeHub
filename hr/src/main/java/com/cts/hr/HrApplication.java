package com.cts.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.cts.hr.*")
@EnableAspectJAutoProxy
public class HrApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);

	}
}	