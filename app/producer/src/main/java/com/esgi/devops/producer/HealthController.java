package com.esgi.devops.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private KafkaTemplate<String, String> template;

    private static int index = 0;

    @GetMapping
    public HealthEnum getHealth() {
        HealthEnum health = HealthEnum.values()[index];
        template.send("esgi", health.toString());

        index = (index + 1) % HealthEnum.values().length;

        return health;
    }
}
