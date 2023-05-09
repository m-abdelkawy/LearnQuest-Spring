package com.student.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.jupiter.api.Assertions.*;


public class StudentServiceTest {
    private ApplicationContext context;
    private StudentService service;

    @BeforeEach
    void setUp() {
        context = new ClassPathXmlApplicationContext("beans.xml");
    }

    @Test
    void testServiceNotNull() {
        service = context.getBean("studentService", StudentService.class);
        assertNotNull(service);
    }

    @Test
    void testDaoInServiceNotNull(){
        service = context.getBean("studentService", StudentService.class);
        assertThat(service.getAllStudents().size(), equalTo(2));
        service.getAllStudents().forEach(s -> {
            System.out.println(String.format("%-10s %-10s%n", s.getFirstName(), s.getSurname()));
        });
    }
}