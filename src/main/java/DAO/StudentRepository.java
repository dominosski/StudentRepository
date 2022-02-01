package DAO;

import Entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository{
    List<Student> findAll();
    Student findById(long id);
    void save(Student student);
    void deleteById(long id);
}
