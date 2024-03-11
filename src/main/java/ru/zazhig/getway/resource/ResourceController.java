package ru.zazhig.getway.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zazhig.getway.resource.model.BaseResource;

import java.time.LocalDate;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Validated
public class ResourceController {
    private final ResourceServer resourceServer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public BaseResource create(@RequestBody ResourceNewDto resourceNewDto) {
        log.info("POST запрос на создание новой заявки");
        return resourceServer.add(resourceNewDto);
    }
}
