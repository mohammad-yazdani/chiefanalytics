package com.chiefanalytics.interpreter.config;

import com.chiefanalytics.interpreter.service.dispatch.ViewDispatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public ViewDispatch viewDispatch() {
        return new ViewDispatch();
    }

<<<<<<< HEAD
=======
    @Bean
    public RestTemplate restTemplate() { return new RestTemplate(); }
>>>>>>> 709009e1713d2ad30d0aab7ca5d9703e16fc468a

}
