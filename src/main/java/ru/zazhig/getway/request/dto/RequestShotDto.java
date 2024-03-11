package ru.zazhig.getway.request.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zazhig.getway.request.RequestStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestShot {

    private String district;//" : "санкт-петербург",

    private String name; //" : "гусь"}],

    private Long count; //" : "3"}

    private RequestStatus status;
}
