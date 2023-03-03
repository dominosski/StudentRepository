package com.example.student.Service;

import com.example.student.DAO.UserRepository;
import com.example.student.Entity.*;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImplementation {
    private final UserRepository userRepository;
    private RoleServiceImplementation roleServiceImplementation;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " was not found"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) throws NotFoundException {
        userRepository.delete(findById(id));
    }

    public User addRoleToUser(Long userId, Long roleId) throws NotFoundException {
        User user = findById(userId);
        Role role = roleServiceImplementation.findById(roleId);
        if(role != null){
            user.addRole(role);
        }
        return userRepository.save(user);
    }
}
