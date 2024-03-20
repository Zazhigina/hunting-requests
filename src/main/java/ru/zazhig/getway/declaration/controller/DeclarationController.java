package ru.zazhig.getway.declaration.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.declaration.dto.DeclarationNew;
import ru.zazhig.getway.declaration.server.DeclarationServer;


@RestController
@RequestMapping("/declaration")
@RequiredArgsConstructor
@Slf4j
@Validated
public class DeclarationController {

    private final DeclarationServer declarationServer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeclarationNew create(@RequestBody @Valid DeclarationDto declarationDto) {
        log.info("POST запрос на создание новой заявки");
        return declarationServer.add(declarationDto);
    }
}
