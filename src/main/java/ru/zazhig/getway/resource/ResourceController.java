package ru.zazhig.getway.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zazhig.getway.resource.model.BaseResource;


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
