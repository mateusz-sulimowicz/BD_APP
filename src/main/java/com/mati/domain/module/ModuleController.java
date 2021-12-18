package com.mati.domain.module;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.mati.domain.product.Product;
import com.mati.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleController(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @GetMapping
    public List<Module> getModules() {
        return moduleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Module getModule(@PathVariable Long id) {
        return moduleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Module> createModule(@RequestBody Module module) throws URISyntaxException {
        Module savedModule = moduleRepository.save(module);
        return ResponseEntity.created(new URI("/api/modules/" + module.getId())).body(savedModule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable Long id, @RequestBody Module module) {
        Module currentModule = moduleRepository.findById(id).orElseThrow(RuntimeException::new);
        currentModule.setName(module.getName());
        currentModule = moduleRepository.save(module);

        return ResponseEntity.ok(currentModule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Module> deleteModule(@PathVariable Long id) {
        moduleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
