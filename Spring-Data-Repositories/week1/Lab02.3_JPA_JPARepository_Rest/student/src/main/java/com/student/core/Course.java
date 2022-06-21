package com.student.core;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSEID")
    private long id;

    private String title;

    private String location;

    @Version
    private long version;

    @ManyToOne
    @JoinColumn(name = "STUDENTID")
    @JsonBackReference
    private Student student;

    public Course() {
        super();
    }

    public Course(String title, String location) {
        super();
        this.title = title;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", version=" + version +
                ", student=" + student +
                '}';
    }

    public String display(){
        return String.format("%n%-10s %-10s %-20s %-20s",
                getStudent().getFirstName(),
                getStudent().getSurname(),
                getLocation(),
                getTitle());
    }


}
