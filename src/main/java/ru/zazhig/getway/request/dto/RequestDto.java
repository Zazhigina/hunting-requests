package ru.zazhig.getway.request.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    @NotBlank(message = "district be empty or null.")
    private String district;//" : "санкт-петербург",
    @NotBlank(message = "resource be empty or null.")
    private String name; //" : "гусь"}],
    @PositiveOrZero(message = "participant limit cannot be negative.")
    private Long count; //" : "3"}
}
