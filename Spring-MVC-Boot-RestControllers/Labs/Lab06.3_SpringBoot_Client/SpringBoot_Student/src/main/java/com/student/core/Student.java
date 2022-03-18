package com.student.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class Student {
    private long id;
    private String firstName;
    private String surname;
    private String dept;
    private double fees;

    public Student() {
    }

    public Student(long id, String firstName, String surname, String dept, double fees) {
        this.id = id;
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
                ", surname='" + surname + '\'' +
                ", dept='" + dept + '\'' +
                ", fees=" + fees +
                '}';
    }
}
