package com.student.service;

import com.student.core.Student;
import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private ApplicationContext context;
    private StudentService service;
    private StudentDao studentDao;

    @BeforeEach
    void setUp() {
        context = new ClassPathXmlApplicationContext("beans.xml");
        service = context.getBean("studentService", StudentService.class);
        //studentDao = context.getBean("studentDao", StudentDao.class);
    }

    @Test
    void testServiceNotNull() {
        //assertNotNull(service);
        System.out.println("Bean names: " + Arrays.toString(context.getBeanNamesForType(StudentService.class)));
    }

    @Test
    void testGetOneStudent() {
        Student student = service.get(1L);
        assertNotNull(student);
        System.out.println(student);
    }

    @Test
    void getAllStudents() {
        Collection<Student> students = service.getAllStudents();
        assertThat(students.size(), equalTo(2));
        students.forEach(System.out::println);
    }
}