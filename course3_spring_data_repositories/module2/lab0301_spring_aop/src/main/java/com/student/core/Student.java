package com.student.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENTID")
    private long id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String surname;
    @Column(name = "DEPARTMENT")
    private String dept;
    @Column(name = "FEES")
    private double fees;

    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Course> courses = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(String firstName, String surname, String dept, double fees) {
        super();
        this.firstName = firstName;
        this.surname = surname;
        this.dept = dept;
        this.fees = fees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    @Override
    public String toString(){
        return "Student{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", surname=" + surname +
                ", dept=" + dept +
                ", fees=" + fees +
                ", courses=" + courses +
                '}';
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
        course.setStudent(this);
    }
}
