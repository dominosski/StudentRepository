package com.example.student.Service;

import com.example.student.DAO.UserRepository;
import com.example.student.Entity.User;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImplementation {
    private UserRepository userRepository;

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
}
