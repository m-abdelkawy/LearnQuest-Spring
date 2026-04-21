package com.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = StudentProperties.class)
@SpringBootApplication
public class SpringBootStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudentApplication.class, args);
	}

}
