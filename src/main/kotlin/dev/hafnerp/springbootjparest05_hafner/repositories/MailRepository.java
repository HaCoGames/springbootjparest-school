package dev.hafnerp.springbootjparest05_hafner.repositories;

import dev.hafnerp.springbootjparest05_hafner.models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
}
