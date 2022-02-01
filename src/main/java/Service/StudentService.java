package Service;

import Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(long id);
    void save(Student student);
    void deleteById(long id);
}
