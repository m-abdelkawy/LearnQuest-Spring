package com.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@EnableConfigurationProperties(value = StudentProperties.class)
@SpringBootApplication
@RestController
@RequestMapping("/")
public class SpringBootStudentApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudentApplication.class, args);
	}

    @GetMapping
    RedirectView home(){
        return new RedirectView("/college/student/msg1");
    }
}
