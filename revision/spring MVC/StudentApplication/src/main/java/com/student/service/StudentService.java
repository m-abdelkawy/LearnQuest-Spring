package com.student.service;

import com.student.core.Student;
import com.student.dao.StudentDao;

import java.util.Collection;

public interface StudentService {
    Student get(long id);
    Collection<Student> getAllStudents();
    Collection<Student> getAllStudentsInDepartment(String department, String lastNameLike);
}
