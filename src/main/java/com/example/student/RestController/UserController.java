package com.example.student.RestController;

import com.example.student.Entity.User;
import com.example.student.Entity.UserCourse;
import com.example.student.Service.UserServiceImplementation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceImplementation userService;

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws NotFoundException {
        return userService.findById(id);
    }

    @PostMapping()
    public User addUser(@RequestBody User user){
        user.setId(0L);
        return userService.save(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) throws NotFoundException {
        userService.delete(id);
    }

    @PutMapping("/{userId}/addRole/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User addRoleToUser(@PathVariable Long userId, @PathVariable String roleId) throws NotFoundException {
        return userService.addRoleToUser(userId, roleId);
    }
}
