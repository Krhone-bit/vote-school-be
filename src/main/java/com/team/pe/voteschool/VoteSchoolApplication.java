package com.team.pe.voteschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.team.pe.voteschool.services", "com.team.pe.voteschool.controllers"})
public class VoteSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteSchoolApplication.class, args);
	}

}
