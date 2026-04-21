package com.student.service;

import com.student.config.ApplicationConfig;
import com.student.core.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class StudentServiceTest {
    //private ApplicationContext context;
    @Inject
    private StudentService service;

//    @BeforeEach
//    void setUp() {
//        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        service = context.getBean("studentService", StudentService.class);
//    }

    @Test
    void testGetOneStudent() {
        Student student = service.get(1L);
        assertThat(student.getFirstName(), equalTo("Eric"));
        //assertNotNull(service);
    }

    @Test
    void testGetAll() {
        assertThat(service.getAllStudents().size(), equalTo(2));
        service.getAllStudents().forEach(s->{
            System.out.printf("%-10s %-10s%n", s.getFirstName(), s.getSurname());
        });
    }
}
