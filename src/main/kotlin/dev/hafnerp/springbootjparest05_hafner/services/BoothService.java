package dev.hafnerp.springbootjparest05_hafner.services;

import dev.hafnerp.springbootjparest05_hafner.models.Booth;

import java.util.List;

public interface BoothService {
    List<Booth> getAllBooths();
    Booth getBooth(Long id);
    Booth createBooth(Booth entity);
    void deleteBooth(Long id);
    Booth updateBooth(Long id, Booth entity);
}
