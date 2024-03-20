package ru.zazhig.getway.declaration.server;

import org.apache.coyote.BadRequestException;
import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.declaration.dto.DeclarationNew;


public interface DeclarationServer {

    DeclarationNew add(DeclarationDto declarationDto) ;
}
