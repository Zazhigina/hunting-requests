package ru.zazhig.getway.resource.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zazhig.getway.resource.dto.ResourceNewDto;
import ru.zazhig.getway.resource.model.BaseResource;
import ru.zazhig.getway.resource.server.ResourceServer;


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
        log.info("POST запрос на создание нового ресурса");
        return resourceServer.add(resourceNewDto);
    }
}
