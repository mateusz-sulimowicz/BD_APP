package com.mati.domain.module;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private final ModuleRepository repository;

    @Autowired
    public ModuleController(ModuleRepository moduleRepository) {
        this.repository = moduleRepository;
    }

    @GetMapping
    public List<Module> getModules() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Module getModule(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Module> createModule(@RequestBody Module module) throws URISyntaxException {
        Module savedModule = repository.save(module);
        return ResponseEntity
                .created(new URI("/api/modules/" + module.getId()))
                .body(savedModule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable Long id, @RequestBody Module module) {
        Module currentModule = repository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        currentModule.setName(module.getName());
        currentModule.setItems(module.getItems());
        currentModule = repository.save(module);

        return ResponseEntity.ok(currentModule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Module> deleteModule(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
