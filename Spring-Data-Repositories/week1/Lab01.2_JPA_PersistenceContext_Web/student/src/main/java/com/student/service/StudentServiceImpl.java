package com.student.service;

import com.student.core.Student;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Named
public class StudentServiceImpl implements  StudentService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Student get(long id) {
        return em.find(Student.class, id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        //the resultSet is to be marshaled directly row by row into student classes
        TypedQuery<Student> typedQuery =
                em.createQuery("SELECT student FROM Student student", Student.class);
        return typedQuery.getResultList();
    }

    @Override
    public Collection<Student> getStudentsByDepartment(String department) {
        TypedQuery<Student> typedQuery=
                em.createQuery("SELECT student FROM Student student WHERE student.dept =: dept", Student.class);
        typedQuery.setParameter("dept", department);
        return typedQuery.getResultList();
    }
}
