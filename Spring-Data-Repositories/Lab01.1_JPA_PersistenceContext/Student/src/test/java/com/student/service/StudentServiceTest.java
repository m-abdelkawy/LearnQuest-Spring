package com.student.service;

import com.student.core.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest
public class StudentServiceTest {
    @Inject
    private StudentService service;

    @Test
    void testServiceSingleStudent(){
        Student student = service.get(1L);
        assertThat(student.getFirstName(), equalTo("Eric"));
        assertThat(student.getSurname(), equalTo("Colbert"));
    }

    @Test
    void testServiceAllStudents(){
        Collection<Student> students = service.getAllStudents();
        students.forEach(student ->{
            String studentPrinted = String.format("%-10d %-10s %-10s", student.getId(),student.getFirstName(), student.getSurname());
            System.out.println(studentPrinted);
        });
    }
}
