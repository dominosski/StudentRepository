package com.example.student.RestController;

import com.example.student.Entity.Student;
import com.example.student.Service.StudentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
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
    public Optional<Student> getStudentById(@PathVariable long id){
        Optional<Student> student = studentService.findById(id);

        if(student == null){
            throw new RuntimeException("Student with id: " + id + " was not found");
        }

        return student;
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student){
        student.setId(0);

        studentService.save(student);

        return student;
    }

    @PutMapping("/student")
    public Student updateStudent(@RequestBody Student student){
        studentService.save(student);

        return student;
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable long id){
        Optional<Student> student = studentService.findById(id);

        if(student == null){
            throw new RuntimeException("Student with id: " + id + " was not found");
        }

        studentService.deleteById(id);

        return "Deleted student with id: " + id;
    }
}
