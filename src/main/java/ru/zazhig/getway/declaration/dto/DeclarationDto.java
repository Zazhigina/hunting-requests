package ru.zazhig.getway.declaration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ru.zazhig.getway.declaration.State;
import ru.zazhig.getway.declaration.Type;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.user.dto.DocumentDto;
import ru.zazhig.getway.user.dto.UserDto;

import java.time.LocalDate;
import java.util.Set;

import static ru.zazhig.getway.declaration.State.NEW;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationDto {
    @NotBlank(message = "first name be empty or null.")
    @Size(min = 2, max = 20, message = "first name cannot be less than 2 or more than 20.")
    private String firstName;
    private String middleName;
    @NotBlank(message = "first name be empty or null.")
    @Size(min = 2, max = 20, message = "first name cannot be less than 2 or more than 20.")
    private String lastName;
    @NotNull
    private Type type;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate issueDate;
    @NotNull(message = "number series id cannot be null.")
    @Positive(message = "number series id cannot be negative or zero.")
    @Min(1)
    @Max(99)
    private Long series;
    @Min(1)
    @Max(99999999)
    @NotNull(message = "number cannot be null.")
    @Positive(message = "number cannot be negative or zero.")
    private Long number;// : "12345678"},
    private Set<RequestDto> requests;
}
