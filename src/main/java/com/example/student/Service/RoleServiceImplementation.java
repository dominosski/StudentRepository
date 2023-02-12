package com.example.student.Service;

import com.example.student.DAO.RoleRepository;
import com.example.student.Entity.Role;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class RoleServiceImplementation {
    private final RoleRepository roleRepository;

    public Role findById(Long id) throws NotFoundException {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found"));
    }
    public Iterable<Role> findAll(){
        return roleRepository.findAll();
    }
}
