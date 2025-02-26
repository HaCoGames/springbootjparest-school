package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEvent(Long id);
    Event createEvent(Event entity);
}
