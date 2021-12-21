package com.mati.domain.option;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    private final OptionRepository repository;

    @Autowired
    public OptionController(OptionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Option> getOptions() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Option getOption(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Option> createOption(@RequestBody Option option) throws URISyntaxException {
        Option savedOption = repository.save(option);
        return ResponseEntity
                .created(new URI("/api/options/" + option.getId()))
                .body(savedOption);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Option> updateOption(@PathVariable Long id, @RequestBody Option option) {
        Option currentOption = repository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        currentOption.setItem(option.getItem());
        currentOption.setModuleId(option.getModuleId());
        currentOption.setPrice(option.getPrice());
        currentOption = repository.save(option);

        return ResponseEntity.ok(currentOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Option> deleteOption(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
