package com.java.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.java.phonebook"}) 
@EnableJpaRepositories
public class PhonebookwebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonebookwebappApplication.class, args);
	}

}
