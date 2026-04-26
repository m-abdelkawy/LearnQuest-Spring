package com.student.service;

import com.student.core.Student;
import com.student.dao.StudentDao;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Collection<Student> getAllStudentsInDepartment(String department, String lastname) {
        return studentDao.getAll().stream()
                .filter(s -> s.getDept().equalsIgnoreCase(department))
                .filter(s -> s.getSurname().contains(lastname.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void addStudent(Student student){
        studentDao.addStudent(student);
//        if(student.getFirstName() != null && student.getSurname() != null && student.getDept() != null){
//        }
    }
}
