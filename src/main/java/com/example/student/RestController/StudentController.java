package com.example.student.RestController;

import com.example.student.Entity.Student;
import com.example.student.Service.StudentServiceImplementation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StudentController {
    private StudentServiceImplementation studentService;

    @Autowired
    public StudentController(StudentServiceImplementation studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public Iterable<Student> findAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable long id) throws NotFoundException {
        return studentService.findById(id);
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student){
        student.setId(0);
        return studentService.save(student);
    }

    @PutMapping("/student")
    public Student updateStudent(@RequestBody Student student){
        return studentService.save(student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable long id) throws NotFoundException {
        studentService.delete(id);
    }
}
