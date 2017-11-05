package com.chiefanalytics.interpreter.config;

import com.chiefanalytics.interpreter.service.dispatch.ViewDispatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ViewDispatch viewDispatch() {
        return new ViewDispatch();
    }


}
