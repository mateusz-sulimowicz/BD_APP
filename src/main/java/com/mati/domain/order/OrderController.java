package com.mati.domain.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository repository;

    @Autowired
    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws URISyntaxException {
        Order savedOrder = repository.save(order);
        return ResponseEntity
                .created(new URI("/api/orders/" + order.getId()))
                .body(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order currentOrder = repository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        currentOrder.setProduct(order.getProduct());
        currentOrder.setDeadline(order.getDeadline());
        currentOrder = repository.save(order);

        return ResponseEntity.ok(currentOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
