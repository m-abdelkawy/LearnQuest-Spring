package com.student.controller;

import com.student.core.Course;
import com.student.core.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@ActiveProfiles("test-postgres")
class StudentControllerTest {

    private final String BASE_URL = "http://localhost:8080";

    @Test
    void testPost() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);

        Student student = new Student();
        student.setFirstName("Mohammed");
        student.setSurname("Abdelkawy");
        student.setDept("Chemistry");

        Course course = new Course();
        course.setLocation("Princeton University");
        course.setTitle("Artificial Intelligence");

        course.setStudent(student);
        student.getCourses().add(course);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try {
            response = restTemplate.postForEntity(BASE_URL + "/student", new HttpEntity<>(student, headers), String.class);
        } catch (HttpServerErrorException e) {
            System.out.println(e.getResponseBodyAsString());
            throw e;
        }
        String resource_location = Objects.requireNonNull(response.getHeaders().getLocation()).toString();
        Student student_added = restTemplate.getForObject(BASE_URL + resource_location, Student.class);
        System.out.println(student_added);
    }
}