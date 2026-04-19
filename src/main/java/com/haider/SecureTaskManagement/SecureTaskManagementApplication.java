package com.haider.SecureTaskManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SecureTaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureTaskManagementApplication.class, args);
	}

}
