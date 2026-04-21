package com.student.service;

import com.student.core.Student;
import com.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.stream.Collectors;

@Component("studentService")
public class StudentServiceImpl implements StudentService{

    @Autowired @Qualifier("studentDaoImpl")
    private StudentDao studentDao;

    @Value("2")
    private int numberOfStudents;

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

//    public void setNumberOfStudents(int numberOfStudents) {
//        this.numberOfStudents = numberOfStudents;
//    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

//    public void setStudentDao(StudentDao studentDao) {
//        this.studentDao = studentDao;
//    }

    @Override
    public Student get(long id) {
        return studentDao.getOne(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentDao.getAll().stream()
                .limit(numberOfStudents)
                .collect(Collectors.toList());
    }
}
