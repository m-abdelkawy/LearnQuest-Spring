package com.student.repository;

import com.student.core.Student;

import java.util.Collection;

public interface StudentDao {
    Student getOne(long id);
    Collection<Student> getAll();

    void add(Student student);
}
