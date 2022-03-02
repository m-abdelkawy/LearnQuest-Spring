package com.student.service;

import com.student.core.Student;
import com.student.dao.StudentDao;

import java.util.Collection;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService{

    private StudentDao studentDao;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student get(long id) {
        return studentDao.getOne(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentDao.getAll();
    }
}
