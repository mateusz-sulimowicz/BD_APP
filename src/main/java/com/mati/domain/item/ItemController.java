package com.mati.domain.item;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final Logger logger = Logger.getLogger(String.valueOf(ItemController.class));
    private final ItemRepository repository;
    private final ItemDAO itemDAO;

    @Autowired
    public ItemController(ItemRepository repository, ItemDAO itemDAO) {
        this.repository = repository;
        this.itemDAO = itemDAO;
    }

    @GetMapping
    public List<Item> getItems() {
        return itemDAO.findAll();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemDAO
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @GetMapping("/orderconfig")
    public Item getItemByOrderAndModule(@RequestParam(name = "orderId") Long orderId,
                                        @RequestParam(name = "moduleId") Long moduleId) {
        return itemDAO
                .findByOrderAndModule(orderId, moduleId)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) throws URISyntaxException {
        Item savedItem = itemDAO.create(item);
        return ResponseEntity
                .created(new URI("/api/items/" + item.getId()))
                .body(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Item currentItem = itemDAO
                .findById(id)
                .orElseThrow(RuntimeException::new);

        currentItem.setName(item.getName());
        currentItem = itemDAO.update(item);

        return ResponseEntity.ok(currentItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id) {
        itemDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
