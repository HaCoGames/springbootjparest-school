package dev.hafnerp.springbootjparest05_hafner.controllers;

import dev.hafnerp.springbootjparest05_hafner.models.*;
import dev.hafnerp.springbootjparest05_hafner.services.MailServiceImpl;
import dev.hafnerp.springbootjparest05_hafner.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MailServiceImpl mailService;

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/{id}/mails/")
    public ResponseEntity<List<Mail>> getMails(@PathVariable Long id) {
        var user = userService.getUser(id);

        if (user instanceof Company) return ResponseEntity.ok(((Company) user).getMails());
        else if (user instanceof Admin) return ResponseEntity.ok (((Admin) user).getMails());

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/participations/")
    public ResponseEntity<List<Participation>> getParticipations(@PathVariable Long id) {
        var user = userService.getUser(id);

        if (user instanceof Company) return ResponseEntity.ok(((Company) user).getParticipations());

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public User create(@RequestBody User entity) {
        return userService.createUser(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { userService.deleteUser(id); }
}
