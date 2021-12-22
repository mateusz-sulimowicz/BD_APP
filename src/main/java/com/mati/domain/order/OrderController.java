package com.mati.domain.order;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderDAO orderDAO;

    @Autowired
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderDAO.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderDAO
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

   @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws URISyntaxException {
       System.out.println(order);
        Order savedOrder = orderDAO.create(order);
        return ResponseEntity
                .created(new URI("/api/orders/" + order.getId()))
                .body(savedOrder);
    }

    /*
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

    @Data
    public static class OrderConfig {
        private Long itemId;
        private Long orderId;
    }*/

}
