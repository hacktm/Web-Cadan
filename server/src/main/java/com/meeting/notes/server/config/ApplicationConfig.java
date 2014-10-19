package com.meeting.notes.server.config;

import com.meeting.notes.server.handlers.ClientsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:mysql.properties")
public class ApplicationConfig {

    @Autowired
    Environment environment;

    @Bean
    public ClientsHandler clientsHandler() {
        return new ClientsHandler();
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("mysql.DRIVER_CLASS_NAME"));
        driverManagerDataSource.setUrl(environment.getProperty("mysql.DATABASE_URL"));
        driverManagerDataSource.setUsername(environment.getProperty("mysql.DATABASE_USERNAME"));
        driverManagerDataSource.setPassword(environment.getProperty("mysql.DATABASE_PASSWORD"));
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(driverManagerDataSource());
    }
}
