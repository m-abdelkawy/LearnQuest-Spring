package com.student.service;

import com.student.core.Student;
import com.student.dao.StudentDao;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentDao studentDao;

    @Override
    public Student get(long id) {
        return studentDao.getOne(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentDao.getAll();
    }
}
