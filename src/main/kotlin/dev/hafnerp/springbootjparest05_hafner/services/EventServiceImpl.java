package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Event;
import dev.hafnerp.springbootjparest05_hafner.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repo;

    @Override
    public List<Event> getAllEvents() {
        return repo.findAll();
    }

    @Override
    public Event getEvent(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Event createEvent(Event entity) {
        return repo.save(entity);
    }
}
