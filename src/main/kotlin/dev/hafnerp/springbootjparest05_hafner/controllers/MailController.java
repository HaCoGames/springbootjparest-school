package dev.hafnerp.springbootjparest05_hafner.controllers;

import dev.hafnerp.springbootjparest05_hafner.models.Mail;
import dev.hafnerp.springbootjparest05_hafner.models.MailDTO;
import dev.hafnerp.springbootjparest05_hafner.services.MailServiceImpl;
import dev.hafnerp.springbootjparest05_hafner.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mails")
public class MailController {

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public List<MailDTO> getAll() {
        return mailService.getAllMails().stream().map(MailDTO::getInstance).toList();
    }

    @GetMapping("/{id}")
    public MailDTO getById(@PathVariable Long id) {
        return MailDTO.getInstance(mailService.getMail(id));
    }

    @PostMapping(value = "/")
    public MailDTO create(@RequestBody MailDTO entity) {
        return MailDTO.getInstance(mailService.createMail(MailDTO.getMailObject(mailService, userService, entity)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { mailService.deleteMail(id); }

    @PutMapping("/{id}")
    public  void update(@PathVariable Long id, @RequestBody MailDTO entity) { mailService.updateMail(id, entity); }
}
