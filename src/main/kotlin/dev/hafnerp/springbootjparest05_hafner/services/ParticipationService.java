package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Participation;

import java.util.List;

public interface ParticipationService {
    List<Participation> getAllParticipations();
    Participation getParticipation(Long id);
    Participation createParticipation(Participation entity);
    void deleteParticipation(Long id);
    Participation updateParticipation(Long id, Participation entity);
}
