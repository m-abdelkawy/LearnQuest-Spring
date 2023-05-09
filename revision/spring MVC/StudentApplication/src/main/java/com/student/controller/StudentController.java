package com.student.controller;

import com.student.core.Student;
import com.student.dao.StudentDao;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Value("${message}")
    private String message;

    @Inject
    private StudentService studentService;

    @GetMapping("msg")
    public String getMessage() {
        return this.message;
    }

    @GetMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Student> getAll() {
        return this.studentService.getAllStudents();
    }

    @GetMapping
    public RedirectView home() {
        return new RedirectView("student/msg");
    }

    @GetMapping("old/{id}")
    public ResponseEntity<Student> getStudent1(@PathVariable("id") long id) {
        Student student = this.studentService.get(id);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("greeting", List.of("Hello World!"));
        return new ResponseEntity<>(student, (MultiValueMap<String, String>) headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent2(@PathVariable("id") long id) {
        Student student = this.studentService.get(id);
        if (student == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().header("greeting", "Hello World new!").body(student);
    }

    @GetMapping("/single")
    public ResponseEntity<Student> getSingleStudent(@RequestParam("id") Optional<Long> optional) {
        long id = optional.orElse(1L);
        Student student = studentService.get(id);
        if(student == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("/search/name/{firstName}")
    public ResponseEntity<Collection<Student>> getStudentsFiltered(@PathVariable("firstName") String firstName,
                                                                   @RequestParam("lastName") Optional<String> lastName){
        Collection<Student> students = studentService
                .getAllStudents()
                .stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                .filter(student -> student.getSurname().toLowerCase().contains(lastName.orElse("").toLowerCase()))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/search/dept/{department}")
    public ResponseEntity<Collection<Student>> getStudentsByDepartment(@PathVariable("department") String department,
                                                                       @RequestParam("lastName") Optional<String> optionalLastName){
        Collection<Student> students = this.studentService.getAllStudentsInDepartment(department, optionalLastName.orElse(""));
        return ResponseEntity.ok().body(students);
    }
}
