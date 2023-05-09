package com.student;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

//	@GetMapping
//	public String home(){
//		return "Hello World";
//	}

	// http://localhost:8080/param?name=Mohamed&age=32
	@GetMapping("/param")
	public String getParams(HttpServletRequest request){
		return request.getParameter("name") + " " + request.getParameter("age");
	}

	@Inject
	private Environment environment;

	@GetMapping("test")
	public String home2(){
		StringBuilder sb = new StringBuilder();
		sb.append("salutation: " + environment.getProperty("salutation"))
				.append("/nJava version: " + environment.getProperty("java.runtime.version"));
		return sb.toString();
	}

	@GetMapping
	RedirectView home(){
		return new RedirectView("student/msg");
	}
}
