package com.student;

import com.student.core.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StudentControllerTest {
    private HttpHeaders headers;
    private String BASE_URL;
    private int PORT;

    @BeforeEach
    void setUp(){
        PORT = 8082;
        BASE_URL = "http://localhost:" + PORT;
        headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void testAdd(){
        Student student = new Student(0, "Susan", "Doubtfire", "French", 75.00);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "/college/student", student, String.class);
        String locationUrl = response.getHeaders().get("location").get(0);
        System.out.println("Location: " + locationUrl);

        ResponseEntity<Student> studentResponseEntity = restTemplate.getForEntity(BASE_URL + locationUrl, Student.class);
        Student studentInserted = studentResponseEntity.getBody();
        System.out.println(studentInserted);
    }

    @Test
    void testAddNegative(){
        Student student = new Student(0, null, "Doubtfire", "French", 75.00);
        Assertions.assertThrows(HttpClientErrorException.class, ()->{
            ResponseEntity<?> responseEntity = new RestTemplate().postForEntity(BASE_URL + "/college/student", student, String.class);
            assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        });
    }

    @Test
    void testGetAll(){
        ResponseEntity<Collection<Student>> responseEntity = new RestTemplate().exchange(BASE_URL + "/college/student",
                HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Student>>() {});

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        responseEntity.getBody().forEach(student->{
            System.out.println(student);
        });
    }

    @Test
    void testGetOnePathParam(){
        ResponseEntity<Student> responseEntity = new RestTemplate().exchange(BASE_URL + "/college/student/{id}",
                HttpMethod.GET, new HttpEntity(headers), Student.class, 3);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        System.out.println(responseEntity.getBody());
    }

    @Test
    void testGetOneRequestParam(){
        ResponseEntity<Student> responseEntity = new RestTemplate()
                .exchange(BASE_URL+"/college/student/single?id={id}", HttpMethod.GET, null, Student.class, 4);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        System.out.println(responseEntity.getBody());
    }

    @Test
    void testGetOneRequestParamXML(){
        headers.set("accept", MediaType.APPLICATION_XML_VALUE);
        ResponseEntity<String> responseEntity = new RestTemplate()
                .exchange(BASE_URL + "/college/student/single?id={id}", HttpMethod.GET, new HttpEntity(headers), String.class, 4);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getHeaders().get("content-type").get(0));
    }

    @Test
    void testGetOneRequestParamJSON(){
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<String> responseEntity = new RestTemplate()
                .exchange(BASE_URL + "/college/student/single?id={id}", HttpMethod.GET, new HttpEntity(headers), String.class, 3);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(responseEntity.getBody());
    }

    @Test
    void testGetOneRequestParamJSONXML(){
        //headers.set("accept", "application/xml, application/json");
        headers.set("accept", "application/json, application/xml");
        ResponseEntity<String> responseEntity = new RestTemplate()
                .exchange(BASE_URL + "/college/student/single?id={id}", HttpMethod.GET, new HttpEntity(headers), String.class, 3);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getHeaders().get("content-type").get(0));
    }

    @Test
    void testGetOneRequestParamHTMLError(){
        //headers.set("accept", "application/xml, application/json");
        headers.set("accept", "application/html");
        Assertions.assertThrows(HttpClientErrorException.class, ()->{
            ResponseEntity<String> responseEntity = new RestTemplate()
                    .exchange(BASE_URL + "/college/student/single?id={id}", HttpMethod.GET, new HttpEntity(headers), String.class, 3);

            assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        });
    }
}
