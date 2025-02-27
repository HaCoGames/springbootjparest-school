package dev.hafnerp.springbootjparest05_hafner.controllers;

import dev.hafnerp.springbootjparest05_hafner.models.Participation;
import dev.hafnerp.springbootjparest05_hafner.services.ParticipationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {

    @Autowired
    private ParticipationServiceImpl participationService;

    @GetMapping("/")
    public List<Participation> getAll() {
        return participationService.getAllParticipations();
    }

    @GetMapping("/{id}")
    public Participation getById(@PathVariable Long id) {
        return participationService.getParticipation(id);
    }

    @PostMapping("/")
    public Participation create(@RequestBody Participation entity) {
        return participationService.createParticipation(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { participationService.deleteParticipation(id); }
}
