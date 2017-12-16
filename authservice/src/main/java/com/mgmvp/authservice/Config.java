package com.mgmvp.authservice;

import com.mgmvp.authservice.DAO.UserDAO;
import com.mgmvp.authservice.DAO.UserDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Bean
    UserDAO userDAO () {
        return new UserDAOImpl();
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/chief_auth");
        dataSource.setUsername( "chief_auth" );
        dataSource.setPassword( "chief_auth" );
        return dataSource;
    }
}
