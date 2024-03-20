package ru.zazhig.getway.declaration.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.zazhig.getway.declaration.Declaration;
import ru.zazhig.getway.declaration.State;
import ru.zazhig.getway.declaration.Type;
import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.declaration.dto.DeclarationNew;
import ru.zazhig.getway.declaration.repository.CourseRequestKey;
import ru.zazhig.getway.declaration.repository.DeclarationRepository;
import ru.zazhig.getway.request.RequestStatus;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.request.dto.RequestShotDto;
import ru.zazhig.getway.request.model.Request;
import ru.zazhig.getway.request.repository.RequestRepository;
import ru.zazhig.getway.resource.model.BaseResource;
import ru.zazhig.getway.resource.model.Resource;
import ru.zazhig.getway.resource.repository.ResourceBaseRepository;
import ru.zazhig.getway.resource.repository.ResourceRepository;
import ru.zazhig.getway.user.dto.UserDto;
import ru.zazhig.getway.user.model.Document;
import ru.zazhig.getway.user.model.User;
import ru.zazhig.getway.user.repository.DocumentsRepository;
import ru.zazhig.getway.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DeclarationServerImplTest {

    @InjectMocks
    DeclarationServerImpl declarationServer;
    @Mock
    private DeclarationRepository declarationRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private DocumentsRepository documentsRepository;
    @Mock
    private ResourceBaseRepository resourceBaseRepository;
    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private RequestRepository requestRepository;

    private DeclarationDto declarationDto;
    public static List<Resource> resources;
    DeclarationNew declarationNew;

    public static Random rand = new Random();

    private User user;
    private Document document;
    private Declaration declaration;
    private List<BaseResource> baseResources;
    private List<Request> requests;


    @BeforeEach
    void setUp() {

        Long count1 = rand.nextLong(15);
        Long count2 = rand.nextLong(10);

        user = User.builder()
                .id(1L)
                .firstName("user1")
                .lastName("last1")
                .middleName("middle1")
                .build();

        document = Document.builder()
                .id(1L)
                .issueDate(LocalDate.of(2023, 01, 01))
                .user(user)
                .series(11L)
                .number(11111111L)
                .build();

        declaration = new Declaration(1L, LocalDate.now(), Type.DRAW, document, State.NEW);

        resources = List.of(new Resource(1L, "гусь", "санкт-петербург"),
                new Resource(2L, "кабан", "санкт-петербург"),
                new Resource(3L, "кролик", "санкт-петербург"),
                new Resource(4L, "лось", "москва"),
                new Resource(5L, "кабан", "москва"),
                new Resource(6L, "кролик", "москва"));

        Set<RequestDto> requestDtos = Set.of(new RequestDto(resources.get(0).getDistrict(), resources.get(0).getName(), count1),
                new RequestDto(resources.get(1).getDistrict(), resources.get(1).getName(), count2));
        requests = List.of(new Request(CourseRequestKey.builder()
                .resource(resources.get(0))
                .declaration(declaration)
                .build(),
                count1,
                RequestStatus.NEW), new Request(CourseRequestKey.builder()
                .resource(resources.get(1))
                .declaration(declaration)
                .build(),
                count2,
                RequestStatus.NEW));

        declarationDto = new DeclarationDto(user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                Type.DRAW,
                document.getIssueDate(),
                document.getSeries(),
                document.getNumber(),
                requestDtos);

        Set<RequestShotDto> requestShotDtos = Set.of(new RequestShotDto(resources.get(0).getDistrict(), resources.get(0).getName(), count1, RequestStatus.NEW),
                new RequestShotDto(resources.get(1).getDistrict(), resources.get(1).getName(), count2, RequestStatus.NEW));

        declarationNew = new DeclarationNew(LocalDate.now(),
                Type.DRAW,
                UserDto.builder()
                        .lastName(user.getLastName())
                        .middleName(user.getMiddleName())
                        .firstName(user.getFirstName()).build(),
                requestShotDtos, State.NEW);


        resourceRepository.saveAll(resources);

        baseResources = List.of(new BaseResource(1L, resources.get(0), 11L, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 2, 1)),
                new BaseResource(2L, resources.get(1), 22L, LocalDate.of(2024, 2, 1), LocalDate.of(2024, 3, 1)),
                new BaseResource(3L, resources.get(2), 33L, LocalDate.of(2023, 3, 1), LocalDate.of(2024, 4, 1)),
                new BaseResource(4L, resources.get(3), 44L, LocalDate.of(2023, 4, 1), LocalDate.of(2024, 3, 1)),
                new BaseResource(5L, resources.get(4), 55L, LocalDate.of(2023, 5, 1), LocalDate.of(2024, 4, 1)),
                new BaseResource(6L, resources.get(5), 66L, LocalDate.of(2023, 6, 1), LocalDate.of(2024, 4, 1)));

        resourceBaseRepository.saveAll(baseResources);

    }

    @Test
    void add() {
        Mockito.when(resourceBaseRepository.findBaseResources())
                .thenReturn(baseResources);

        Mockito.when(declarationRepository.save(any()))
                .thenReturn(declaration);
        Mockito.when(requestRepository.saveAll(any()))
                .thenReturn(requests);

        Mockito.when(userRepository.save(any()))
                .thenReturn(user);

        Mockito.when(documentsRepository.save(any()))
                .thenReturn(document);

        DeclarationNew declarationNew1 = declarationServer.add(declarationDto);

        assertNotNull(declarationNew);
        assertEquals(declarationNew.getUser(), declarationNew1.getUser());
        assertEquals(declarationNew.getType(), declarationNew1.getType());
        assertEquals(declarationNew.getState(), declarationNew1.getState());
        assertEquals(declarationNew.getCreatedOn(), declarationNew1.getCreatedOn());
        assertEquals(declarationNew.getRequests(), declarationNew1.getRequests());

        verify(userRepository, atMostOnce()).saveAndFlush(any());
        verify(declarationRepository, atMostOnce()).saveAndFlush(any());
        verify(documentsRepository, atMostOnce()).saveAndFlush(any());
    }


}
