package com.student.service;

import com.student.core.Student;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Named
public class StudentServiceImpl implements StudentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Student get(long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s from Student s", Student.class);
        return query.getResultList();
    }
}