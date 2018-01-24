package com.esgi.devops.producer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Autowired
    private KafkaProducerProperties kafkaProducerProperties;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), stringKeySerializer(), stringKeySerializer());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootstrap());
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> workUnitsKafkaTemplate() {
        KafkaTemplate<String, String> kafkaTemplate =  new KafkaTemplate<>(producerFactory());
        return kafkaTemplate;
    }

    @Bean
    public Serializer stringKeySerializer() {
        return new StringSerializer();
    }
}