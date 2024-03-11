package ru.zazhig.getway.declaration.mapper;

import lombok.experimental.UtilityClass;
import ru.zazhig.getway.declaration.Declaration;
import ru.zazhig.getway.declaration.State;
import ru.zazhig.getway.declaration.Type;
import ru.zazhig.getway.declaration.dto.DeclarationNew;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.request.dto.RequestShotDto;
import ru.zazhig.getway.user.dto.UserDto;
import ru.zazhig.getway.user.model.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static ru.zazhig.getway.declaration.State.REVIEW;

@UtilityClass
public class DeclarationMapper {
    public Declaration toDeclaration(Type type, Document document) {
        return Declaration.builder()
                .createdOn(LocalDate.now())
                .document(document)
                .type(type)
                .requests(Set.of())
                .state(State.NEW)
                .build();
    }

    public DeclarationNew toDeclarationNew(Declaration declaration, UserDto user, Set<RequestShotDto> request) {
        return DeclarationNew.builder()
                .createdOn(declaration.getCreatedOn())
                .type(declaration.getType())
                .user(user)
                .requests(request)
                .state(declaration.getState())
                .build();
    }



    public void toNewState(List<Declaration> declarations) {
        declarations.forEach(e -> e.setState(REVIEW));
    }
}
