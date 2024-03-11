package ru.zazhig.getway.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private String lastName;
    private String firstName;
    private String middleName;
}
