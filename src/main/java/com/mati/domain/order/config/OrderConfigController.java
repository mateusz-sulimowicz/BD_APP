package com.mati.domain.order.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/orderConfigs")
public class OrderConfigController {

    private final OrderConfigRepository repository;

    @Autowired
    public OrderConfigController(OrderConfigRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<OrderConfig> getOrderConfigs() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public OrderConfig getOrderConfig(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<OrderConfig> createOrderConfig(@RequestBody OrderConfig orderConfig) throws URISyntaxException {
        OrderConfig savedOrderConfig = repository.save(orderConfig);
        return ResponseEntity
                .created(new URI("/api/orderConfigs/" + orderConfig.getId()))
                .body(savedOrderConfig);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderConfig> updateOrderConfig(@PathVariable Long id, @RequestBody OrderConfig orderConfig) {
        OrderConfig currentOrderConfig = repository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        currentOrderConfig.setItemId(orderConfig.getItemId());
        currentOrderConfig.setOrderId(orderConfig.getOrderId());
        currentOrderConfig.setItemId(orderConfig.getItemId());
        currentOrderConfig = repository.save(orderConfig);

        return ResponseEntity.ok(currentOrderConfig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderConfig> deleteOrderConfig(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Data
    public static class OrderConfigConfig {
        private Long itemId;
        private Long OrderConfigId;
    }

}
