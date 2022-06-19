package com.example.student.Service;

import com.example.student.DAO.StudentRepository;
import com.example.student.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImplementation{
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateDBWithData(){
        System.out.println("Testing db population");
        Student student = new Student("TestName", "TestLastName", "96166");

        save(student);
    }
}
