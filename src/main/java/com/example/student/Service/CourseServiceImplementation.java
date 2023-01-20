package com.example.student.Service;

import com.example.student.DAO.CourseRepository;
import com.example.student.Entity.Course;
import com.example.student.Entity.Professor;
import com.example.student.Entity.Student;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImplementation {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentServiceImplementation studentServiceImplementation;
    @Autowired
    private ProfessorServiceImplementation professorServiceImplementation;

    public boolean checkIfCourseExists(long id) throws NotFoundException {

        return true;
    }

    public Course get (long id) throws NotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found"));
    }

    public Iterable<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(long id) throws NotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course with given id was not found"));
    }
    public Course save(Course course){
        return courseRepository.save(course);
    }
    public Course update(Course course) throws NotFoundException {
        checkIfCourseExists(course.getId());
        return courseRepository.save(course);
    }

    public void deleteById(long id) throws NotFoundException {
        checkIfCourseExists(id);
        courseRepository.deleteById(id);
    }

    public Course addStudentToCourse(long courseId, long studentId) throws NotFoundException {
        checkIfCourseExists(courseId);
        Student student = studentServiceImplementation.findById(studentId);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        Course course = get(courseId);
        course.setStudentList(studentList);
        return courseRepository.save(course);
    }

    public Course addProfessorToCourse(long id, long professorId) throws NotFoundException {
        checkIfCourseExists(id);
        Professor professor = professorServiceImplementation.findById(professorId);
        Course course = get(id);
        course.setProfessor(professor);
        return courseRepository.save(course);
    }
}
