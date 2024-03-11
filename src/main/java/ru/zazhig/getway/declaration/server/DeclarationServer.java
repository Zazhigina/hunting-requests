package ru.zazhig.getway.declaration.server;

import ru.zazhig.getway.declaration.Declaration;
import ru.zazhig.getway.declaration.Type;
import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.declaration.dto.DeclarationNew;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.user.dto.DocumentDto;
import ru.zazhig.getway.user.dto.UserDto;

import java.util.Set;

public interface DeclarationServer {

    DeclarationNew add(DeclarationDto declarationDto);
}
