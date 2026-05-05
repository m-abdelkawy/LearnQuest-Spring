package com.student.service;

import com.student.core.Student;
import com.student.dao.StudentRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

@Named
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentRepository studentRepository;

    @Override
    public Student get(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> getStudentsByDepartment(String department) {
        return studentRepository.getAllByDept(department);
    }


}