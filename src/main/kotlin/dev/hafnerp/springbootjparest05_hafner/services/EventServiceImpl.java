package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Event;
import dev.hafnerp.springbootjparest05_hafner.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public void deleteEvent(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Event updateEvent(Long id, Event entity) {
        return repo.findById(id).map(existingEvent -> {
            existingEvent.set_when(entity.get_when());
            existingEvent.setLabel(entity.getLabel());
            existingEvent.setBoothPrice(entity.getBoothPrice());
            existingEvent.setBigBoothPrice(entity.getBigBoothPrice());
            return repo.save(existingEvent);
        }).orElseThrow(() -> new EntityNotFoundException("Event with id " + id + " not found"));
    }

}
