package com.example.student.RestController;

import com.example.student.Entity.User;
import com.example.student.Service.UserServiceImplementation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private UserServiceImplementation userService;

    @GetMapping("/user")
    public Iterable<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable long id) throws NotFoundException {
        return userService.findById(id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        user.setId(0L);
        return userService.save(user);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id) throws NotFoundException {
        userService.delete(id);
    }

    @PutMapping("/user/{userId}/addRole/{roleId}")
    public User addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) throws NotFoundException {
        return userService.addRoleToUser(userId, roleId);
    }
}
