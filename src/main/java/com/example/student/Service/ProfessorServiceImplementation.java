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


    public Iterable<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(Long id) throws NotFoundException {
        return professorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Professor with given id does not exist"));
    }
    public Professor save(Professor course){
        return professorRepository.save(course);
    }
    public Professor update(Professor professor) throws NotFoundException {
        return professorRepository.save(professor);
    }

    public void delete(Long id) throws NotFoundException {
        professorRepository.delete(findById(id));
    }
}
