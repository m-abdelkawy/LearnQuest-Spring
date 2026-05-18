package com.student.controller;

import com.student.core.Course;
import com.student.core.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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


    // before adding the @ExceptionHandler in the controller
    @Test
    void testPostNegative() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);

        Student student = new Student();
        student.setFirstName("Mohammed");
        student.setSurname("Abdelkawy");
        student.setDept("Chemistry");

        student.setFees(201.00);

        Course course = new Course();
        course.setLocation("Princeton University");
        course.setTitle("Artificial Intelligence");

        course.setStudent(student);
        student.getCourses().add(course);

        RestTemplate restTemplate = new RestTemplate();

        HttpServerErrorException ex = Assertions.assertThrows(HttpServerErrorException.class,() -> {
            ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "/student", new HttpEntity<>(student, headers), String.class);
        });
        assertThat(ex.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    // after adding the @ExceptionHandler to the controller
    @Test
    void testPostNegative2() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);

        Student student = new Student();
        student.setFirstName("Mohammed");
        student.setSurname("Abdelkawy");
        student.setDept("Chemistry");

        student.setFees(201.00);

        Course course = new Course();
        course.setLocation("Princeton University");
        course.setTitle("Artificial Intelligence");

        course.setStudent(student);
        student.getCourses().add(course);

        RestTemplate restTemplate = new RestTemplate();

        HttpClientErrorException ex = Assertions.assertThrows(HttpClientErrorException.class,() -> {
            ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "/student", new HttpEntity<>(student, headers), String.class);
        });
        assertThat(ex.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
    }
}