package com.example.fastfood.controller;

import com.example.fastfood.entity.Order;
import com.example.fastfood.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final KafkaProducerService kafkaProducerService;
    private Map<Long, Order> orders = new HashMap<>();
    private Long idCounter = 0L;

    @Autowired
    public OrderController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order.setId(++idCounter);
        orders.put(order.getId(), order);
        kafkaProducerService.sendMessage("orderTopic", "Created order: " + order.toString());
        return order;
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable long id, @RequestBody Order order) {
        if(!orders.containsKey(id)){
            order.setId(id);
        }
        orders.put(id, order);
        kafkaProducerService.sendMessage("orderTopic", "Updated order: " + order.toString());
        return order;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        if (orders.containsKey(id)) {
            orders.remove(id);
            kafkaProducerService.sendMessage("orderTopic", "Deleted order with ID: " + id);
        }
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        if (orders.containsKey(id)) {
            return orders.get(id);
        }
        return null;
    }

}