package com.mgmvp.authservice;

import com.mgmvp.authservice.DAO.UserDAO;
import com.mgmvp.authservice.DAO.UserDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    UserDAO userDAO () {
        return new UserDAOImpl();
    }
}
