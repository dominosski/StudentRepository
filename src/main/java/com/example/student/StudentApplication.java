package com.example.student;

import DAO.StudentRepository;
import Entity.Student;
import Entity.StudentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.student.*;

@SpringBootApplication
public class StudentApplication {

    //TODO - create repo instance
    /*private StudentRepository studentRepository;

    @Autowired
    public StudentApplication(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }*/

    public static void main(String[] args) {


        SpringApplication.run(StudentApplication.class, args);

        Student student = new Student("Dominik", "Kijowski", "96112");

        StudentDetails studentDetails = new StudentDetails("Computer Science", "Osiedle Pomorskie street",
                "787787787", "test1@gmail.com", "Group-11");

        student.setStudentDetails(studentDetails);


    }

}
