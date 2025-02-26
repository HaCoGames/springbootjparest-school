package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.User;
import dev.hafnerp.springbootjparest05_hafner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUser(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User entity) {
        return repo.save(entity);
    }
}
