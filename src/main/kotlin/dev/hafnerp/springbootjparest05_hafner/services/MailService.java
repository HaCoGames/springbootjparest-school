package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Mail;
import dev.hafnerp.springbootjparest05_hafner.models.MailDTO;

import java.util.List;

public interface MailService {
    List<Mail> getAllMails();
    Mail getMail(Long id);
    Mail createMail(Mail entity);
    void deleteMail(Long id);
    Mail updateMail(Long id, MailDTO entity);
}
