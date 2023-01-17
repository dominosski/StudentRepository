package com.example.student.Service;

import com.example.student.DAO.CourseRepository;
import com.example.student.Entity.Course;
import com.example.student.Entity.Student;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImplementation {
    @Autowired
    private CourseRepository courseRepository;

    public boolean checkIfUserExists(long id) throws NotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if(!course.isPresent()){
            throw new NotFoundException("Course with given id was not found");
        }
        return true;
    }

    public Iterable<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(long id) throws NotFoundException {
        checkIfUserExists(id);
        return courseRepository.findById(id);
    }
    public Course save(Course course){
        return courseRepository.save(course);
    }
    public Course update(Course course) throws NotFoundException {
        checkIfUserExists(course.getId());
        return courseRepository.save(course);
    }

    public void deleteById(long id) throws NotFoundException {
        checkIfUserExists(id);
        courseRepository.deleteById(id);
    }
}
