package ru.zazhig.getway.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/check")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void review(@RequestParam(name = "check", required = false) Boolean check) {
        log.info("POST запрос на создание новой заявки");
        reviewService.review(check);
    }
}
