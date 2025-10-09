package com.cts.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication(scanBasePackages = "com.cts.hr.*")
@EnableMethodSecurity
@EnableCaching
public class HrApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);

	}
}	