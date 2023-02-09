package com.example.student.Entity;

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
    @ManyToMany(mappedBy = "courseList", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<User> userList = new HashSet<>();

    public void addUser(User user) {
        this.userList.add(user);
        user.getCourseList().add(this);
    }
    public void removeUser(User user){
        this.userList.remove(user);
        user.getCourseList().remove(this);
    }
}
