package com.example.student.RestController;

import com.example.student.Entity.Professor;
import com.example.student.Service.ProfessorServiceImplementation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProfessorController {
    @Autowired
    private ProfessorServiceImplementation professorServiceImplementation;

    @GetMapping("/professor")
    public Iterable<Professor> findAllProfessors(){
        return professorServiceImplementation.findAll();
    }

    @GetMapping("/professor/{id}")
    public Professor findProfessorById(@PathVariable long id) throws NotFoundException {
        return professorServiceImplementation.findById(id);
    }

    @PostMapping("/professor")
    public Professor addProfessor(@RequestBody Professor professor){
        professor.setId(0);
        return professorServiceImplementation.save(professor);
    }

    @PutMapping("/professor")
    public Professor updateProfessor(@RequestBody Professor professor) throws NotFoundException {
        return professorServiceImplementation.update(professor);
    }

    @DeleteMapping("/professor/{id}")
    public void deleteProfessor(@PathVariable long id) throws NotFoundException {
        professorServiceImplementation.delete(id);
    }

}
