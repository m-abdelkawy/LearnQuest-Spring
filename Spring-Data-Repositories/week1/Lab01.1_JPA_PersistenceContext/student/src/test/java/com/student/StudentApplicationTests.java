package com.student;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.student.core.Student;
import com.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;
import java.util.Collection;

@SpringBootTest
class StudentApplicationTests {

    @Inject
    private StudentService studentService;

    @Test
    void testServiceSingle() {
        Student student = studentService.get(1L);
        assertThat(student.getFirstName(), equalTo("Eric"));
        assertThat(student.getSurName(), equalTo("Colbert"));
    }

    @Test
    void testService() {
        Collection<Student> students = studentService.getAllStudents();
        students.forEach(student -> {
            System.out.printf("%-10s %10s%n", student.getFirstName(), student.getSurName());
        });
    }
}
