package dev.hafnerp.springbootjparest05_hafner.controllers;

import dev.hafnerp.springbootjparest05_hafner.models.Mail;
import dev.hafnerp.springbootjparest05_hafner.services.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mails")
public class MailController {

    @Autowired
    private MailServiceImpl mailService;

    @GetMapping("/")
    public List<Mail> getAll() {
        return mailService.getAllMails();
    }

    @GetMapping("/{id}")
    public Mail getById(@PathVariable Long id) {
        return mailService.getMail(id);
    }

    @PostMapping("/")
    public Mail create(@RequestBody Mail entity) {
        return mailService.createMail(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { mailService.deleteMail(id); }
}
