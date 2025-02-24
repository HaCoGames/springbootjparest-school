package dev.hafnerp.springbootjparest05_hafner.repositories;

import dev.hafnerp.springbootjparest05_hafner.models.Booth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothRepository extends JpaRepository<Booth, Long> {
}
