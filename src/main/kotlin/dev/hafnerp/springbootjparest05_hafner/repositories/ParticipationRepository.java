package dev.hafnerp.springbootjparest05_hafner.repositories;

import dev.hafnerp.springbootjparest05_hafner.models.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
