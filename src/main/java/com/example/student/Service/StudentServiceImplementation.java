package com.example.student.Service;

import com.example.student.DAO.StudentRepository;
import com.example.student.Entity.Student;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImplementation{
    @Autowired
    private StudentRepository studentRepository;

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) throws NotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id: " + id + " was not found"));
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Long id) throws NotFoundException {
        studentRepository.delete(findById(id));
    }
}
