package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Mail;
import dev.hafnerp.springbootjparest05_hafner.repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailRepository repo;

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
}
