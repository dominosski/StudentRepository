package com.example.student.Service;

import com.example.student.DAO.CourseRepository;
import com.example.student.Entity.Course;
import com.example.student.Entity.User;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CourseServiceImplementation {
    private final CourseRepository courseRepository;
    private final UserServiceImplementation userServiceImplementation;

    public Iterable<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) throws NotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course with given id was not found"));
    }
    public Course save(Course course){
        return courseRepository.save(course);
    }
    public Course update(Course course) throws NotFoundException {
        findById(course.getId());
        return courseRepository.save(course);
    }

    public void deleteById(Long id) throws NotFoundException {
        findById(id);
        courseRepository.deleteById(id);
    }

    public Course addUserToCourse(Long courseId, Long userId) throws NotFoundException {
        User user = userServiceImplementation.findById(userId);
        Course course = findById(courseId);
        course.addUser(user);
        return courseRepository.save(course);
    }

    public Course removeUserFromCourse(Long courseId, Long userId) throws NotFoundException {
        User user = userServiceImplementation.findById(userId);
        Course course = findById(courseId);
        course.removeUser(user);
        return courseRepository.save(course);
    }

}
