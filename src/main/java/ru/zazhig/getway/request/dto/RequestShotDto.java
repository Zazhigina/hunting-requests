package ru.zazhig.getway.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zazhig.getway.request.model.RequestStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestShotDto {

    private String district;//" : "санкт-петербург",

    private String name; //" : "гусь"}],

    private Long count; //" : "3"}

    private RequestStatus status;
}
