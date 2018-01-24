package com.esgi.devops.producer;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(value = "/")
public class RootController {

    @Autowired
    private KafkaTemplate<String, String> template;

    @GetMapping()
    public String getRoot() {
        String message = "J'aime les licornes et leurs jolies cornes";
        template.send("esgi", message);

        return message;
    }
}
