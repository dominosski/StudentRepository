package com.example.student.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users_courses")
@AllArgsConstructor
@NoArgsConstructor
public class UserCourse {
    @EmbeddedId
    private UserCourseId id;

    private Double grade;
}
