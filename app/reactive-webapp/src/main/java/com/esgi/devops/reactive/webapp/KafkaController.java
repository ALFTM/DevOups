package com.esgi.devops.reactive.webapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class KafkaController {

    @Autowired
    private ReactiveStorage storage;

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "esgi", groupId = "kafka")
    public void receive(String payload) {
        log.info("received payload='{}'", payload);
        latch.countDown();
        storage.setOrigin(payload);
        storage.setReactive(ReactiveStorage.calculate(payload));
    }
}
