package dev.hafnerp.springbootjparest05_hafner.repositories;

import dev.hafnerp.springbootjparest05_hafner.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
