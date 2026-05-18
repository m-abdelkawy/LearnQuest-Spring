package com.student.service;

import com.student.core.Student;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface StudentService {
    Student get(long id);
    Collection<Student> getAllStudents();
    Collection<Student> getStudentsByDepartment(String department);

    void save(Student student);
}
