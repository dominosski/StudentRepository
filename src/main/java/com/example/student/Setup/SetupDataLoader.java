package com.example.student.Setup;

import com.example.student.DAO.PrivilegeRepository;
import com.example.student.DAO.RoleRepository;
import com.example.student.Entity.Privilege;
import com.example.student.Entity.Role;
import com.example.student.Entity.User;
import com.example.student.Service.UserServiceImplementation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SetupDataLoader implements ApplicationListener {
    boolean alreadySetup = false;
    @Autowired
    private UserServiceImplementation userServiceImplementation;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationEvent event) {

        if(alreadySetup)
            return;
        Privilege readPrivilege = createPrivilege("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilege("WRITE_PRIVILEGE");
        Set<Privilege> adminPrivileges = Stream.of(readPrivilege, writePrivilege).collect(Collectors.toSet());
        createRole("ROLE_ADMIN", adminPrivileges);
        createRole("ROLE_USER", Stream.of(readPrivilege).collect(Collectors.toSet()));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("TestName");
        user.setLastName("TestLastName");
        user.setIndexNumber("91662");
        user.setYear(3);
        userServiceImplementation.save(user);

        try {
            user = userServiceImplementation.findById(1L);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        user.setRoles(Stream.of(adminRole).collect(Collectors.toSet()));
        userServiceImplementation.save(user);
        alreadySetup = true;
    }

    private Role createRole(String name, Set<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if(role == null){
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    private Privilege createPrivilege(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if(privilege == null){
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
}
