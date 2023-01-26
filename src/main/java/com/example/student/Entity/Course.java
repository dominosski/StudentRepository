package com.example.student.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    @ManyToMany(mappedBy = "courseList", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Student> studentList = new HashSet<>();

    public void addStudent(Student student){
        this.studentList.add(student);
        student.getCourseList().add(this);
    }
    public void removeStudent(Student student){
        this.studentList.remove(student);
        student.getCourseList().remove(this);
    }

    public void addProfessor(Professor professor){
        this.setProfessor(professor);
        professor.getCourseList().add(this);
    }
    public void removeProfessor(Professor professor){
        this.setProfessor(null);
        professor.getCourseList().remove(this);
    }
}
