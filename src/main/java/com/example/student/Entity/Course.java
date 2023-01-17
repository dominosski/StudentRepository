package com.example.student.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private String courseName;
    @ManyToOne
    private Professor professorName;
    @ManyToMany(mappedBy = "courseList")
    private List<Student> studentList;

}
