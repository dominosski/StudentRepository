package com.example.student.Service;

import com.example.student.DAO.StudentRepository;
import com.example.student.Entity.Student;
import com.example.student.Entity.StudentDetails;
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
        Student student = new Student("Dominik", "Kijowski", "96112");

//        StudentDetails studentDetails = new StudentDetails("Computer Science", "Osiedle Pomorskie street",
//                "787787787", "test1@gmail.com", "Group-11");
//
//        student.setStudentDetails(studentDetails);

        save(student);
    }
}
