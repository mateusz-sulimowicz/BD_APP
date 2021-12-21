package com.mati.domain.order;


import com.mati.domain.product.ProductRepository;
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
    private final ProductRepository productRepository;

    @Autowired
    public OrderController(OrderRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
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
        System.out.println(order);
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
        currentOrder.setValue(order.getValue());
        currentOrder.setOrderDate(order.getOrderDate());
        currentOrder = repository.save(order);

        return ResponseEntity.ok(currentOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
