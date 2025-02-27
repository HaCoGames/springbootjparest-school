package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Booth;
import dev.hafnerp.springbootjparest05_hafner.repositories.BoothRepository;
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
}
