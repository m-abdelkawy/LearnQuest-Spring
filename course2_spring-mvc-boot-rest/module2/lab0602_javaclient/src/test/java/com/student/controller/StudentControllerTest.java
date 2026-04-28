package com.student.controller;

import com.student.core.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class StudentControllerTest {
    private String port = "8090";

    private final String BASE_URL = "http://localhost:" + port;

    private final String STUDENT_URL = BASE_URL + "/college/student/";

    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAddStudent() {
        RestTemplate restTemplate = new RestTemplate();
        Student student = new Student();
        student.setFirstName("Mohammed");
        student.setSurname("Abdelkawy");
        student.setDept("Engineering");



        // contains the header with the location of the resource posted
        ResponseEntity<String> response = restTemplate.postForEntity(STUDENT_URL, new HttpEntity<Student>(student, headers), String.class);

        String locationUrl = Objects.requireNonNull(response.getHeaders().getLocation(), "resource location is not present").toString();

        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(BASE_URL + locationUrl, Student.class);

        System.out.println(responseEntity.getBody());
    }

    @Test
    void testAddNegative(){
        Student student = new Student(0, null, "Ali", "French", 75.00);
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            ResponseEntity<?> responseEntity = new RestTemplate().postForEntity(STUDENT_URL, student, String.class);
            assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        });
    }

    @Test
    void testAddNegative2(){
        Student student = new Student(0, null, "Ali", "French", 75.00);
        HttpClientErrorException ex = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            ResponseEntity<?> responseEntity = new RestTemplate().postForEntity(STUDENT_URL, student, String.class);
        });
        assertThat(ex.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
    }

    @Test
    void testListOfStudents(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Student>> responseEntity =
                restTemplate.exchange(
                        STUDENT_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Student>>() {});

        List<Student> students = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        responseEntity.getBody().forEach(System.out::println);
    }
}