package com.student.service;

import com.student.core.Course;
import com.student.core.Student;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;

public class StudentControllerRestTemplateTest {
    @Test
    void testPost(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);

        Student student = new Student();
        student.setId(5L);
        student.setFirstName("Ahmed");
        student.setSurname("ALi");
        student.setDept("Structural");
        student.setFees(122.00);

        Course course = new Course();
        course.setLocation("University of Illinois");
        course.setStudent(student);
        course.setTitle("Steel Design");
        student.getCourses().add(course);


//        ResponseEntity<String> response = new RestTemplate()
//                .postForEntity("http://localhost:8080/student", new HttpEntity(student,headers), String.class);


        //tring url = response.getHeaders().get("location").get(0);

//        Student attendee = new RestTemplate()
//                .getForObject("http://localhost:8080"+url, Student.class);

        Student attendee = new RestTemplate()
                .getForObject("http://localhost:8080/student/1", Student.class);

        System.out.println(attendee);
    }
}
