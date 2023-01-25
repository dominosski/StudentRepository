package com.example.student.Service;

import com.example.student.DAO.CourseRepository;
import com.example.student.Entity.Course;
import com.example.student.Entity.Professor;
import com.example.student.Entity.Student;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
@AllArgsConstructor
public class CourseServiceImplementation {
    private final CourseRepository courseRepository;
    private final StudentServiceImplementation studentServiceImplementation;
    private final ProfessorServiceImplementation professorServiceImplementation;

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

    public Course addStudentToCourse(Long courseId, Long studentId) throws NotFoundException {
        Student student = studentServiceImplementation.findById(studentId);
        Course course = findById(courseId);
        course.addStudent(student);
        return courseRepository.save(course);
    }
    public Course removeStudentFromCourse(Long courseId, Long studentId) throws NotFoundException {
        Student student = studentServiceImplementation.findById(studentId);
        Course course = findById(courseId);
        course.removeStudent(student);
        return courseRepository.save(course);
    }

    public Course addProfessorToCourse(Long id, Long professorId) throws NotFoundException {
        Professor professor = professorServiceImplementation.findById(professorId);
        Course course = findById(id);
        course.setProfessor(professor);
        return courseRepository.save(course);
    }
}
