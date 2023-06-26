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

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " was not found"));
    }

    public User save(User user) {
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    public void delete(Long id) throws NotFoundException {
        userRepository.delete(findById(id));
    }

    public User addRoleToUser(Long userId, String role) throws NotFoundException {
        User user = findById(userId);
        if(role.equals("ADMIN".toUpperCase())){
            user.setRole(Role.ROLE_ADMIN);
        }else{
            user.setRole(Role.ROLE_USER);
        }
        return userRepository.save(user);
    }
}
