package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Booth;
import dev.hafnerp.springbootjparest05_hafner.repositories.BoothRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoothServiceImpl implements BoothService {

    @Autowired
    private BoothRepository repo;

    @Override
    public List<Booth> getAllBooths() {
        return repo.findAll();
    }

    @Override
    public Booth getBooth(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Booth createBooth(Booth entity) {
        return repo.save(entity);
    }

    @Override
    public void deleteBooth(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Booth updateBooth(Long id, Booth entity) {
        return repo.findById(id).map(existingBooth -> {
            existingBooth.setNr(entity.getNr());
            existingBooth.setSize(entity.getSize());
            existingBooth.setBig(entity.getBig());
            existingBooth.setTop(entity.getTop());
            existingBooth.setLeft(entity.getLeft());
            existingBooth.setWidth(entity.getWidth());
            existingBooth.setHeight(entity.getHeight());
            return repo.save(existingBooth);
        }).orElseThrow(() -> new EntityNotFoundException("Booth with id " + id + " not found"));
    }
}
