package ru.zazhig.getway;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.zazhig.getway.declaration.Type;
import ru.zazhig.getway.declaration.controller.DeclarationController;
import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.declaration.dto.DeclarationNew;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.resource.model.BaseResource;
import ru.zazhig.getway.resource.model.Resource;
import ru.zazhig.getway.resource.repository.ResourceRepository;
import ru.zazhig.getway.user.model.Document;
import ru.zazhig.getway.user.model.User;
import ru.zazhig.getway.user.repository.DocumentsRepository;
import ru.zazhig.getway.user.repository.UserRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;


@SpringBootApplication
@Configuration
@AllArgsConstructor
@EnableScheduling
public class GetwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetwayApplication.class, args);
    }
}
