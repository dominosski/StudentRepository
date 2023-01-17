package com.example.student.Service;

import com.example.student.DAO.StudentRepository;
import com.example.student.Entity.Student;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImplementation{
    @Autowired
    private StudentRepository studentRepository;

    public boolean checkIfStudentExists(long id) throws NotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent()){
            throw new NotFoundException("Student with id: " + id + " was not found");
        }
        return true;
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(long id) throws NotFoundException {
        checkIfStudentExists(id);
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(long id) throws NotFoundException {
        checkIfStudentExists(id);
        studentRepository.deleteById(id);
    }
}
