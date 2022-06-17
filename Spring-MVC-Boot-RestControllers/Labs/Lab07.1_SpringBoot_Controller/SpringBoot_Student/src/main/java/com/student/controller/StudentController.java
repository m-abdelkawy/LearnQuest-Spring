package com.student.controller;

import com.student.StudentProperties;
import com.student.core.Student;
import com.student.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
    @Inject
    private StudentProperties studentProperties;

    @Inject
    private StudentService studentService;

    @GetMapping("/msg1")
    public String getMessage() {
        return studentProperties.getGreeting();
    }

    @GetMapping("/msg")
    public String getHeader(@RequestHeader("user-agent") String userAgent) {
        return studentProperties.getGreeting() + ",   " + userAgent;
    }

    @GetMapping
    public Collection<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") long id) {
        return studentService.get(id);
    }

    @GetMapping(path = "/single",
    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Student getSingleStudent(@RequestParam("id") Optional<Long> optionalLong) {
        return studentService.get(optionalLong.orElse(1L));
    }

    @GetMapping("/search/{department}")
    public Collection<Student> getStudentsPerDepartment(@PathVariable("department") String department,
                                                        @RequestParam("lastNameLike") Optional<String> optionalLastNameLike) {
        return studentService.getAllStudentsInDepartment(department, optionalLastNameLike.orElse(""));
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        studentService.add(student);
        ResponseEntity<String> res;
        if(student.getId() > 0){
            URI uri = URI.create("/college/student/" + student.getId());
            res = ResponseEntity.accepted().location(uri).build();
        }else{
            res = ResponseEntity.badRequest().build();
        }
        return res;
    }
}
