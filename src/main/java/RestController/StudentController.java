package RestController;

import Entity.Student;
import Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable long id){
        Student student = studentService.findById(id);

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
        Student student = studentService.findById(id);

        if(student == null){
            throw new RuntimeException("Student with id: " + id + " was not found");
        }

        studentService.deleteById(id);

        return "Deleted student with id: " + id;
    }
}
