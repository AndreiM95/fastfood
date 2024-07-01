package com.example.fastfood.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void triggerCreateOrder() {
        sendMessage("orderTopic", "{\"event\":\"order_created\",\"details\":\"Created order: Burger\"}");
    }

    public void triggerUpdateOrder() {
        sendMessage("orderTopic", "{\"event\":\"order_updated\",\"details\":\"Updated order: Burger\"}");
    }

    public void triggerDeleteOrder() {
        sendMessage("orderTopic", "{\"event\":\"order_deleted\",\"details\":\"Deleted order with ID: 1\"}");
    }
}
