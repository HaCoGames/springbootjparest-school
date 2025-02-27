package dev.hafnerp.springbootjparest05_hafner.controllers;

import dev.hafnerp.springbootjparest05_hafner.models.Event;
import dev.hafnerp.springbootjparest05_hafner.services.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    @GetMapping("/")
    public List<Event> getAll() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getById(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @PostMapping("/")
    public Event create(@RequestBody Event entity) {
        return eventService.createEvent(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { eventService.deleteEvent(id); }
}
