package ru.zazhig.getway.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentsDto {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate documentIssueDate;
    @NotNull(message = "number series id cannot be null.")
    @Positive(message = "number series id cannot be negative or zero.")
    @Size(min = 2, max = 2, message = "series cannot be less than 2 or more than 2.")
    private Long seriesOfDocuments;
    @NotNull(message = "number cannot be null.")
    @Positive(message = "number cannot be negative or zero.")
    @Size(min = 8, max = 8, message = "number cannot be less than 8 or more than 8.")
    private Long numberOfDocuments;// : "12345678"},

}
