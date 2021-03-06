package com.student.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentServiceTest {
    private ApplicationContext context;
    private StudentService service;

    @BeforeEach
    void setUp(){
        context = new ClassPathXmlApplicationContext("beans.xml");
//        service = context.getBean("");
    }

    @Test
    void testGetOneStudent(){
        assertNotNull(service);
    }

    @Test
    void testGetAll(){
        assertThat(service.getAllStudents().size(), equalTo(2));

        service.getAllStudents().forEach(s->{
            System.out.printf("%-10s %-10s%n", s.getFirstName(), s.getSurname());
        });
    }
}
