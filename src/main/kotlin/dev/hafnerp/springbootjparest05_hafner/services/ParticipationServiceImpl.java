package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Participation;
import dev.hafnerp.springbootjparest05_hafner.repositories.ParticipationRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public Participation updateParticipation(Long id, Participation entity) {
        return repo.findById(id).map(existingParticipation -> {
            existingParticipation.setPrice(entity.getPrice());
            existingParticipation.setPaidUntilNow(entity.getPaidUntilNow());
            existingParticipation.setComments(entity.getComments());
            existingParticipation.setIF(entity.getIF());
            existingParticipation.setIT(entity.getIT());
            existingParticipation.setBT(entity.getBT());
            existingParticipation.setIR(entity.getIR());
            existingParticipation.setCntTables(entity.getCntTables());
            existingParticipation.setNeedsPower(entity.getNeedsPower());
            existingParticipation.setOwnBooth(entity.getOwnBooth());
            existingParticipation.setNeedsSpace(entity.getNeedsSpace());

            // Update relationships if needed
            existingParticipation.setBooth(entity.getBooth());
            existingParticipation.setEvent(entity.getEvent());
            existingParticipation.setCompany(entity.getCompany());

            return repo.save(existingParticipation);
        }).orElseThrow(() -> new EntityNotFoundException("Participation with id " + id + " not found"));
    }

}
