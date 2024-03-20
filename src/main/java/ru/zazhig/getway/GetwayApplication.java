package ru.zazhig.getway;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@AllArgsConstructor
@EnableScheduling
public class GetwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetwayApplication.class, args);
    }
}
