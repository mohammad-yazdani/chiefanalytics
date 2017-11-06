package com.chiefanalytics.interpreter.service.dispatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public abstract class Dispatch {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private String service;

    Dispatch(String service) {
        this.service = service;
    }

    public void send(String payload) {
        kafkaTemplate.send(this.service, payload);
    }
}
