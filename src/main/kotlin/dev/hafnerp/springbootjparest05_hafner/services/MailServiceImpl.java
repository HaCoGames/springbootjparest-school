package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Company;
import dev.hafnerp.springbootjparest05_hafner.models.Mail;
import dev.hafnerp.springbootjparest05_hafner.models.MailDTO;
import dev.hafnerp.springbootjparest05_hafner.repositories.MailRepository;
import dev.hafnerp.springbootjparest05_hafner.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailRepository repo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Mail> getAllMails() {
        return repo.findAll();
    }

    @Override
    public Mail getMail(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Mail createMail(Mail entity) {
        return repo.save(entity);
    }

    @Override
    public void deleteMail(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Mail updateMail(Long id, MailDTO dto) {
        return repo.findById(id).map(existingMail -> {
            existingMail.setWhenSent(dto.getWhenSent());
            existingMail.setSubject(dto.getSubject());
            existingMail.setContent(dto.getContent());
            existingMail.setReceivers(dto.getReceivers().stream()
                    .map(userRepository::getById)
                    .filter(Company.class::isInstance)
                    .map(Company.class::cast)
                    .collect(Collectors.toList()));
            return repo.save(existingMail);
        }).orElseThrow(() -> new EntityNotFoundException("Mail with id " + id + " not found"));
    }

}
