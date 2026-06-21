package com.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getAll() {

        List<Order> order = orderService.getAll();

        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {

        Order order = orderService.getById(id);

        if(order == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        Order newOrder = orderService.createOrder(order);

        if(newOrder == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {

        Order updateOrder = orderService.updateOrder(id, order);

        if(updateOrder == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
