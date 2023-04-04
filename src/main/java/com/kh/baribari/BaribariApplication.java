package com.kh.baribari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BaribariApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaribariApplication.class, args);
	}

}
