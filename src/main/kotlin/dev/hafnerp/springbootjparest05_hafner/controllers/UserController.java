package dev.hafnerp.springbootjparest05_hafner.controllers;

import dev.hafnerp.springbootjparest05_hafner.models.User;
import dev.hafnerp.springbootjparest05_hafner.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/")
    public User create(@RequestBody User entity) {
        return userService.createUser(entity);
    }
}
