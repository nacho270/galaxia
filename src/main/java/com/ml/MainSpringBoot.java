package com.ml;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MainSpringBoot {

    public static void main(final String[] args) throws SQLException {
        SpringApplication.run(MainSpringBoot.class, args);
    }
}
