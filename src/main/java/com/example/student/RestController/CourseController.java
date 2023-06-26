package com.example.student.RestController;

import com.example.student.Entity.Course;
import com.example.student.Entity.User;
import com.example.student.Service.CourseServiceImplementation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseController {
    private final CourseServiceImplementation courseServiceImplementation;

    @GetMapping
    public Iterable<Course> findAllCourses(){
        return courseServiceImplementation.findAll();
    }
    @GetMapping("/{id}")
    public Course findCourseById(@PathVariable long id) throws NotFoundException {
        return courseServiceImplementation.findById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        course.setId(0L);
        return courseServiceImplementation.save(course);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Course updateCourse(@RequestBody Course course) throws NotFoundException {
        return courseServiceImplementation.update(course);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCourse(@PathVariable Long id) throws NotFoundException {
        courseServiceImplementation.deleteById(id);
    }

    @PutMapping("/{courseId}/addUser/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Course addUserToCourse(@PathVariable Long courseId, @PathVariable Long userId) throws NotFoundException {
        return courseServiceImplementation.addUserToCourse(courseId, userId);
    }

    @DeleteMapping("/{courseId}/removeUser/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Course removeUserFromCourse(@PathVariable Long courseId, @PathVariable Long userId) throws NotFoundException {
        return courseServiceImplementation.removeUserFromCourse(courseId, userId);
    }
}
