package com.student.controller;

import com.student.core.Student;
import com.student.repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/student")
@RestController
@CrossOrigin
public class StudentController {
    @Inject
    private StudentRepository studentRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable("id") long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @GetMapping(path = "/search/department", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getDepartments() {
        return studentRepository.findAll()
                .stream()
                .map(s -> s.getDept())
                .distinct()
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/search/department/{department}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Student> getStudentsByDepartment(@PathVariable("department") String department){
        return studentRepository.getByDept(department);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Student student){
        studentRepository.save(student);
        return ResponseEntity.accepted().header("location", "/student/"+student.getId())
                .build();
    }
}
