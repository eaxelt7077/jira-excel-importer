package com.axelapp.jiraexcelimporter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaPublisher.class);

    private static final String KAKFA_TOPIC = "axelapp";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        LOG.info("Queuing {}", message);
        kafkaTemplate.send(KAKFA_TOPIC, message);
    }
}
