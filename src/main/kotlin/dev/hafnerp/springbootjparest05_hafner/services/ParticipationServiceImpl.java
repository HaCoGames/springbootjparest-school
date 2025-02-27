package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Participation;
import dev.hafnerp.springbootjparest05_hafner.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    private ParticipationRepository repo;

    @Override
    public List<Participation> getAllParticipations() {
        return repo.findAll();
    }

    @Override
    public Participation getParticipation(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Participation createParticipation(Participation entity) {
        return repo.save(entity);
    }

    @Override
    public void deleteParticipation(Long id) {
        repo.deleteById(id);
    }
}
