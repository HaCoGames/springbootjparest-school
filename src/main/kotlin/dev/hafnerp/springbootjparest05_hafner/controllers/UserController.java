package dev.hafnerp.springbootjparest05_hafner.controllers;

import dev.hafnerp.springbootjparest05_hafner.models.*;
import dev.hafnerp.springbootjparest05_hafner.services.MailServiceImpl;
import dev.hafnerp.springbootjparest05_hafner.services.ParticipationService;
import dev.hafnerp.springbootjparest05_hafner.services.ParticipationServiceImpl;
import dev.hafnerp.springbootjparest05_hafner.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private ParticipationService participationService;

    @GetMapping("/")
    public List<UserDTO> getAll() {
        return userService.getAllUsers().stream().map(user -> {
            if (user instanceof Admin) return AdminDTO.getInstance((Admin) user);
            else if (user instanceof Company) return CompanyDTO.getInstance((Company) user);
            else return new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEMail(),
                        user.getPwdToken(),
                        new ArrayList<>(),
                        "user",
                        null,
                        null,
                        null,
                        null,
                        null
                );
        }).toList();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        User foundUser = userService.getUser(id);
        UserDTO returnValue = new UserDTO(
                foundUser.getId(),
                foundUser.getName(),
                foundUser.getEMail(),
                foundUser.getPwdToken(),
                new ArrayList<>(),
                "user",
                null,
                null,
                null,
                null,
                null
        );

        if (foundUser instanceof Admin) returnValue = AdminDTO.getInstance((Admin) foundUser);
        else if (foundUser instanceof Company) returnValue = CompanyDTO.getInstance((Company) foundUser);

        return returnValue;
    }

    @GetMapping("/{id}/mails/")
    public ResponseEntity<List<MailDTO>> getMails(@PathVariable Long id) {
        var user = userService.getUser(id);

        if (user instanceof Company) return ResponseEntity.ok(((Company) user).getMails().stream().map(MailDTO::getInstance).toList());
        else if (user instanceof Admin) return ResponseEntity.ok (((Admin) user).getMails().stream().map(MailDTO::getInstance).toList());

        return ResponseEntity.notFound().build();
    }

    //TODO("Do when participationDTO is done!")
    @GetMapping("/{id}/participations/")
    public ResponseEntity<List<Participation>> getParticipations(@PathVariable Long id) {
        var user = userService.getUser(id);

        if (user instanceof Company) return ResponseEntity.ok(((Company) user).getParticipations());

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public UserDTO create(@RequestBody UserDTO entity) {
        String type = entity.getType();
        User userToCreate = null;

        System.out.println(type);

        if ("admin".equals(type)) {
            Admin admin = new Admin(entity.getPwdToken(), entity.getEMail(), entity.getName());
            admin.setMails(entity.getMails().stream().map(m -> MailDTO.getMailObject(mailService, userService, m)).toList());

            userToCreate = admin;
        }
        else if ("company".equals(type)) {
            Company company = new Company(
                    entity.getPwdToken(),
                    entity.getEMail(),
                    entity.getName(),
                    entity.getResponsible(),
                    entity.getPhone(),
                    entity.getCcTo(),
                    entity.getComments()
            );
            company.setMails(entity.getMails().stream().map(m -> MailDTO.getMailObject(mailService, userService, m)).toList());
            company.setParticipations(entity.getParticipations().stream().map(p -> participationService.getParticipation(p)).toList());

            userToCreate = company;

        }
        else {
            userToCreate = new User(entity.getPwdToken(), entity.getEMail(), entity.getName());
            userToCreate.setId(entity.getId());
        }

        System.out.println(userToCreate.eMail);

        User createdUser = userService.createUser(userToCreate);

        return switch (type) {
            case "admin" -> AdminDTO.getInstance((Admin) createdUser);
            case "company" -> CompanyDTO.getInstance((Company) createdUser);
            default -> new UserDTO(
                    createdUser.getId(),
                    createdUser.getName(),
                    createdUser.getEMail(),
                    createdUser.getPwdToken(),
                    new ArrayList<>(),
                    "user",
                    null,
                    null,
                    null,
                    null,
                    null
            );
        };
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { userService.deleteUser(id); }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO entity) {
        String type = entity.getType();
        User userToUpdate;

        if ("admin".equals(type)) {
            Admin admin = new Admin(entity.getPwdToken(), entity.getEMail(), entity.getName());
            admin.setMails(entity.getMails().stream().map(m -> MailDTO.getMailObject(mailService, userService, m)).toList());

            userToUpdate = admin;
        }
        else if ("company".equals(type)) {
            Company company = new Company(
                    entity.getPwdToken(),
                    entity.getEMail(),
                    entity.getName(),
                    entity.getResponsible(),
                    entity.getPhone(),
                    entity.getCcTo(),
                    entity.getComments()
            );
            company.setMails(entity.getMails().stream().map(m -> MailDTO.getMailObject(mailService, userService, m)).toList());
            company.setParticipations(entity.getParticipations().stream().map(p -> participationService.getParticipation(p)).toList());

            userToUpdate = company;
        }
        else {
            userToUpdate = new User(entity.getPwdToken(), entity.getEMail(), entity.getName());
        }

        userToUpdate.setId(id); // Ensure we update the correct entity

        User updatedUser = userService.updateUser(id, userToUpdate);

        UserDTO response = switch (type) {
            case "admin" -> AdminDTO.getInstance((Admin) updatedUser);
            case "company" -> CompanyDTO.getInstance((Company) updatedUser);
            default -> new UserDTO(
                    updatedUser.getId(),
                    updatedUser.getName(),
                    updatedUser.getEMail(),
                    updatedUser.getPwdToken(),
                    new ArrayList<>(),
                    "user",
                    null,
                    null,
                    null,
                    null,
                    null
            );
        };

        return ResponseEntity.ok(response);
    }

}
