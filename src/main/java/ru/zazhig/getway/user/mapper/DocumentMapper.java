package ru.zazhig.getway.user.mapper;

import lombok.experimental.UtilityClass;

import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.user.model.Document;
import ru.zazhig.getway.user.model.User;

@UtilityClass
public class DocumentMapper {
    public Document toDocument(DeclarationDto declarationDto, User user) {
        return Document.builder()
                .issueDate(declarationDto.getIssueDate())
                .series(declarationDto.getSeries())
                .number(declarationDto.getNumber())
                .user(user)
                .build();
    }
}
