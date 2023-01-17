package com.example.student.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Professor {
    @GeneratedValue
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String degree;
    @OneToMany(mappedBy = "professorName")
    private List<Course> courseList;
}
