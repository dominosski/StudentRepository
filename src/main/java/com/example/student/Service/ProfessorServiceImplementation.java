package com.example.student.Service;

import com.example.student.DAO.CourseRepository;
import com.example.student.DAO.ProfessorRepository;
import com.example.student.Entity.Course;
import com.example.student.Entity.Professor;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorServiceImplementation {
    @Autowired
    private ProfessorRepository professorRepository;

    public boolean checkIfUserExists(long id) throws NotFoundException {
        Optional<Professor> course = professorRepository.findById(id);
        if(!course.isPresent()){
            throw new NotFoundException("Professor with given id was not found");
        }
        return true;
    }

    public Iterable<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findById(long id) throws NotFoundException {
        checkIfUserExists(id);
        return professorRepository.findById(id);
    }
    public Professor save(Professor course){
        return professorRepository.save(course);
    }
    public Professor update(Professor course) throws NotFoundException {
        checkIfUserExists(course.getId());
        return professorRepository.save(course);
    }

    public void deleteById(long id) throws NotFoundException {
        checkIfUserExists(id);
        professorRepository.deleteById(id);
    }
}
