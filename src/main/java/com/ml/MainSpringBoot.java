package com.ml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase principal para correr el sistema con spring-boot.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class MainSpringBoot {

    /**
     * Metodo main.
     *
     * @param args
     *            {@link String[]} parametros.
     */
    public static void main(final String[] args) {
        SpringApplication.run(MainSpringBoot.class, args);
    }
}
