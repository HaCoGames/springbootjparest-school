package dev.hafnerp.springbootjparest05_hafner.controllers;

import dev.hafnerp.springbootjparest05_hafner.models.Booth;
import dev.hafnerp.springbootjparest05_hafner.services.BoothServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booths")
public class BoothController {

    @Autowired
    private BoothServiceImpl boothService;

    @GetMapping("/")
    public List<Booth> getAll() {
        return boothService.getAllBooths();
    }

    @GetMapping("/{id}")
    public Booth getById(@PathVariable Long id) {
        return boothService.getBooth(id);
    }

    @PostMapping("/")
    public Booth create(@RequestBody Booth entity) {
        return boothService.createBooth(entity);
    }
}
