package com.student.core;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL)
    private Collection<Course> courses = new ArrayList<Course>();

    public Student() {
        super();
    }

    public Student(String firstName, String surname, String dept, double fees) {
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
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surname + '\'' +
                ", dept='" + dept + '\'' +
                ", fees=" + fees +
                '}';
    }

    // methods to interact with Collection of Courses
    public Collection<Course> getCourses(){
        return courses;
    }

    public void add(Course course){
        courses.add(course);
    }
}