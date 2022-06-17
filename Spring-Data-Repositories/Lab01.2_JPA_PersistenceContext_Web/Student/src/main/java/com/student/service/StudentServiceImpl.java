package com.student.service;

import com.student.core.Student;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.stream.Collectors;

@Named
public class StudentServiceImpl implements StudentService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Student get(long id) {
        return em.find(Student.class, id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        TypedQuery<Student> query = em.createQuery("SELECT student FROM Student student", Student.class);
        return query.getResultList();
    }

    @Override
    public Collection<Student> getStudentsByDepartment(String department) {
        return getAllStudents()
                .stream()
                .filter(student -> student.getDept().toLowerCase().equals(department.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsByDepartment2(String department) {
        TypedQuery<Student> query = em.createQuery("SELECT student FROM Student student WHERE student.dept = :dept", Student.class);
        query.setParameter("dept", department);
        return query.getResultList();
    }
}
