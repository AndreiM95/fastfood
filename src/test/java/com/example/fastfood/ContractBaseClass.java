package com.example.fastfood;

import com.example.fastfood.kafka.KafkaProducerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public abstract class ContractBaseClass {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    public void triggerCreateOrder() {
        kafkaProducerService.triggerCreateOrder();
    }

    public void triggerUpdateOrder() {
        kafkaProducerService.triggerUpdateOrder();
    }

    public void triggerDeleteOrder() {
        kafkaProducerService.triggerDeleteOrder();
    }
}
