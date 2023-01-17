package com.example.student.RestController;

import com.example.student.Entity.Course;
import com.example.student.Service.CourseServiceImplementation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseServiceImplementation courseServiceImplementation;

    @GetMapping("/course")
    public Iterable<Course> findAllCourses(){
        return courseServiceImplementation.findAll();
    }
    @GetMapping("/course/{id}")
    public Optional<Course> findCourseById(@PathVariable long id) throws NotFoundException {
        return courseServiceImplementation.findById(id);
    }

    @PostMapping("/course")
    public Course createCourse(@RequestBody Course course) {
        course.setId(0);
        return courseServiceImplementation.save(course);
    }

    @PutMapping("/course")
    public Course updateCourse(@RequestBody Course course) throws NotFoundException {
        return courseServiceImplementation.update(course);
    }
    @DeleteMapping("/course/{id}")
    public void deleteCourse(@PathVariable long id) throws NotFoundException {
        courseServiceImplementation.deleteById(id);
    }
}
