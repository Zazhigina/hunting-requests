package ru.zazhig.getway.user.mapper;

import lombok.experimental.UtilityClass;

import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.user.dto.UserDto;
import ru.zazhig.getway.user.model.User;
;

@UtilityClass
public class UserMapper {

    public User toUser(DeclarationDto declarationDto) {
        return User.builder()
                .firstName(declarationDto.getFirstName())
                .middleName(declarationDto.getMiddleName())
                .lastName(declarationDto.getLastName())
                .build();
    }

    public UserDto toUser(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .build();
    }

}
