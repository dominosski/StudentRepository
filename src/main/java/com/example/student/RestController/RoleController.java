package com.example.student.RestController;

import com.example.student.Entity.Role;
import com.example.student.Service.RoleServiceImplementation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleController {
    private RoleServiceImplementation roleServiceImplementation;

    @GetMapping("/role")
    public Iterable<Role> getAllRoles(){
        return roleServiceImplementation.findAll();
    }

    @GetMapping("/role/{roleId}")
    public Role getRoleById(@PathVariable Long roleId) throws NotFoundException{
        return roleServiceImplementation.findById(roleId);
    }


}
