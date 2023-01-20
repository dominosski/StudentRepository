package com.example.student.RestController;

import com.example.student.Entity.Course;
import com.example.student.Entity.Professor;
import com.example.student.Entity.Student;
import com.example.student.Service.CourseServiceImplementation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseServiceImplementation courseServiceImplementation;

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
        course.setId(0);
        return courseServiceImplementation.save(course);
    }

    @PutMapping
    public Course updateCourse(@RequestBody Course course) throws NotFoundException {
        return courseServiceImplementation.update(course);
    }
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable long id) throws NotFoundException {
        courseServiceImplementation.deleteById(id);
    }

    @PostMapping("/{id}/addStudent/{studentId}")
    public Course addStudentToCourse(@PathVariable long courseId, @RequestBody long studentId) throws NotFoundException {
        return courseServiceImplementation.addStudentToCourse(courseId, studentId);
    }

    @PostMapping("/{id}/addProfessor/{professorId}")
    public Course addProfessorToCourse(@PathVariable long courseId, @PathVariable long professorId) throws NotFoundException {
        return courseServiceImplementation.addProfessorToCourse(courseId, professorId);
    }
}
