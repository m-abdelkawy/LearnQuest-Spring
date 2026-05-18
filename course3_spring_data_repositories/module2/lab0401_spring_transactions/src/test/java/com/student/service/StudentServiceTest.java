//package com.student.service;
//
//import com.student.core.Student;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import javax.inject.Inject;
//
//import java.util.Collection;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//@SpringBootTest
//@ActiveProfiles("test-postgres")
//public class StudentServiceTest {
//
//    @Inject
//    private StudentService studentService;
//
//    @Test
//    void testServiceSingle(){
//        Student student = studentService.get(1L);
//        assertThat(student.getFirstName(), equalTo("Eric"));
//        assertThat(student.getSurname(), equalTo("Colbert"));
//    }
//
//    @Test
//    void testServiceAllStudents(){
//        Collection<Student> students = studentService.getAllStudents();
//        students.forEach(System.out::println);
//    }
//
//    @Test
//    void testServiceByDepartment() {
//        Collection<Student> students = studentService.getStudentsByDepartment("Chemistry");
//        students.forEach(System.out::println);
//    }
//}
