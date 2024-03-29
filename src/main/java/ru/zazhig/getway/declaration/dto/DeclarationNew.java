package ru.zazhig.getway.declaration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zazhig.getway.declaration.model.State;
import ru.zazhig.getway.declaration.model.Type;
import ru.zazhig.getway.request.dto.RequestShotDto;
import ru.zazhig.getway.user.dto.UserDto;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeclarationNew {

    private LocalDate createdOn;

    private Type type;

    private UserDto user;

    private Set<RequestShotDto> requests;

    private State state;
}
