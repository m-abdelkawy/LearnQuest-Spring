package com.student.config;

import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import com.student.service.StudentService;
import com.student.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Value("2")
    private int numberOfStudents;

    @Bean("studentService")
    StudentService getStudentService(){
        StudentServiceImpl service = new StudentServiceImpl();
        service.setStudentDao(getStudentDao());
        service.setNumberOfStudents(this.numberOfStudents);
        return service;
    }

    @Bean("studentDao")
    StudentDao getStudentDao(){
        return new StudentDaoImpl();
    }
}
