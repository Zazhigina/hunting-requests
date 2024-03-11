package ru.zazhig.getway.declaration.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zazhig.getway.declaration.Declaration;
import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.declaration.dto.DeclarationNew;
import ru.zazhig.getway.declaration.mapper.DeclarationMapper;
import ru.zazhig.getway.declaration.repository.DeclarationRepository;
import ru.zazhig.getway.request.dto.RequestShotDto;
import ru.zazhig.getway.request.model.Request;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.request.mapper.RequestMapper;
import ru.zazhig.getway.request.repository.RequestRepository;
import ru.zazhig.getway.resource.ResourceMapper;
import ru.zazhig.getway.resource.model.BaseResource;
import ru.zazhig.getway.resource.model.Resource;
import ru.zazhig.getway.resource.repository.ResourceBaseRepository;
import ru.zazhig.getway.user.dto.UserDto;
import ru.zazhig.getway.user.mapper.DocumentMapper;
import ru.zazhig.getway.user.mapper.UserMapper;
import ru.zazhig.getway.user.model.Document;
import ru.zazhig.getway.user.model.User;
import ru.zazhig.getway.user.repository.DocumentsRepository;
import ru.zazhig.getway.user.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeclarationServerImpl implements DeclarationServer {
    private final DeclarationRepository declarationRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final DocumentsRepository documentsRepository;
    private final ResourceBaseRepository resourceBaseRepository;

    @Override
    public DeclarationNew add(DeclarationDto declarationDto) {
        User user = userRepository.save(UserMapper.toUser(declarationDto));
        Document document = documentsRepository.save(DocumentMapper.toDocument(declarationDto, user));
        Declaration declaration = DeclarationMapper.toDeclaration(declarationDto.getType(), document);
        List<BaseResource> resources = resourceBaseRepository.findBaseResources();

        Set<Request> request = declarationDto.getRequests().stream()
                .map(e -> RequestMapper.toRequestNew(declaration, toResourceNew(ResourceMapper.toResource(e), resources), e))
                .collect(Collectors.toSet());
        declaration.setRequests(request);
        declarationRepository.save(declaration);
        request.stream().map(requestRepository::save).collect(Collectors.toSet());

        UserDto userDto = UserMapper.toUser(user);

        Set<RequestShotDto> requestShotDto = declaration.getRequests().stream().map(RequestMapper::toRequestDto).collect(Collectors.toSet());
        return DeclarationMapper.toDeclarationNew(declaration, userDto, requestShotDto);
    }

    public Resource toResourceNew(Resource resource, List<BaseResource> resources) {
        for (BaseResource resource1 : resources) {
            if (resource1.getResource().getDistrict().equals(resource.getDistrict()) && resource1.getResource().getName().equals(resource.getName())) {
                return resource1.getResource();
            }
        }
        return null;
    }

}
