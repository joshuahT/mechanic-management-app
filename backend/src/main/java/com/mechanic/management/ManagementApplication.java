package com.mechanic.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagementApplication {

	public static void main(String[] args) {
		System.out.println("SPRINGM_USER: " + System.getenv("SPRINGM_USER"));
		System.out.println("SPRINGM_PASS: " + System.getenv("SPRINGM_PASS"));

		SpringApplication.run(ManagementApplication.class, args);
	}

}

