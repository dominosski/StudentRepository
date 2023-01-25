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
@EqualsAndHashCode(exclude = "studentList")
@ToString(exclude = "studentList")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    @ManyToOne
    private Professor professor;
    @ManyToMany(mappedBy = "courseList", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Set<Student> studentList = new HashSet<>();

    public void addStudent(Student student){
        this.studentList.add(student);
        student.getCourseList().add(this);
    }
    public void removeStudent(Student student){
        this.studentList.remove(student);
        student.getCourseList().remove(this);
    }
}
