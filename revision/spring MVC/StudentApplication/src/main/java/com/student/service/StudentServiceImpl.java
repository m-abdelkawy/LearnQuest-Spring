package com.student.service;

import com.student.core.Student;
import com.student.dao.StudentDao;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class StudentServiceImpl implements StudentService {
    @Inject
    private StudentDao studentDao;

    @Override
    public Student get(long id) {
        return this.studentDao.getOne(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return this.studentDao.getAll();
    }

    @Override
    public Collection<Student> getAllStudentsInDepartment(String department, String lastNameLike) {
        return this.studentDao
                .getAll()
                .stream()
                .filter(student -> student.getDept().equalsIgnoreCase(department))
                .filter(student -> student.getSurname().toLowerCase().contains(lastNameLike.toLowerCase()))
                .collect(Collectors.toList());
    }
}
