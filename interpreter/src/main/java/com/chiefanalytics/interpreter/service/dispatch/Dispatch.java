package com.chiefanalytics.interpreter.service.dispatch;

import com.chiefanalytics.interpreter.model.InputFile;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;

@EnableBinding(Source.class)
public abstract class Dispatch {

    String service;

    Dispatch(String service) {
        this.service = service;
    }

    @Bean
    @InboundChannelAdapter(channel = Source.OUTPUT)
    public abstract String send(InputFile payload);
}
