package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Company;
import dev.hafnerp.springbootjparest05_hafner.models.User;
import dev.hafnerp.springbootjparest05_hafner.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User entity) {
        return repo.findById(id).map(existingUser -> {
            existingUser.setName(entity.getName());
            existingUser.setEMail(entity.getEMail());

            // Avoid overwriting password token unless it's a reset process
            if (entity.getPwdToken() != null && !entity.getPwdToken().isEmpty()) {
                existingUser.setPwdToken(entity.getPwdToken());
            }

            if (existingUser instanceof Company existingCompany && entity instanceof Company newCompany) {
                existingCompany.setResponsible(newCompany.getResponsible());
                existingCompany.setPhone(newCompany.getPhone());
                existingCompany.setCcTo(newCompany.getCcTo());
                existingCompany.setComments(newCompany.getComments());
            }

            return repo.save(existingUser);
        }).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }


}
