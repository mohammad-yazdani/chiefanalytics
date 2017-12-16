package com.chiefanalytics.interpreter.service.dispatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public abstract class Dispatch {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

<<<<<<< HEAD
    /**
     * The name of topic
     */
=======
>>>>>>> 709009e1713d2ad30d0aab7ca5d9703e16fc468a
    private String service;

    Dispatch(String service) {
        this.service = service;
    }

<<<<<<< HEAD

    public void send(String payload) {
        // TODO : Fix this later !!!
=======
    public void send(String payload) {
>>>>>>> 709009e1713d2ad30d0aab7ca5d9703e16fc468a
        kafkaTemplate.send(this.service, payload);
    }
}
