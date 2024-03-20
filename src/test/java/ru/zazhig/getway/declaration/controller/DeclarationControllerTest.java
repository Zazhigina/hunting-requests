package ru.zazhig.getway.declaration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.zazhig.getway.declaration.State;
import ru.zazhig.getway.declaration.Type;
import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.declaration.dto.DeclarationNew;
import ru.zazhig.getway.declaration.server.DeclarationServer;
import ru.zazhig.getway.request.RequestStatus;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.request.dto.RequestShotDto;
import ru.zazhig.getway.resource.model.Resource;
import ru.zazhig.getway.user.dto.UserDto;
import ru.zazhig.getway.user.model.Document;
import ru.zazhig.getway.user.model.User;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeclarationController.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = {"db.name=test"})
class DeclarationControllerTest {
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DeclarationServer declarationServer;

    @Autowired
    private MockMvc mvc;

    private DeclarationDto declarationDto;
    public static List<Resource> resources;
    DeclarationNew declarationNew;

    public static Random rand = new Random();

    private User user;
    private Document document;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

        resources = List.of(new Resource(1L, "кролик", "санкт-петербург"),
                new Resource(2L, "гусь", "москва"));

        Set<RequestDto> requestDtos = Set.of(new RequestDto(resources.get(0).getDistrict(), resources.get(0).getName(), count1),
                new RequestDto(resources.get(1).getDistrict(), resources.get(1).getName(), count2));

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
        ;

        declarationNew = new DeclarationNew(LocalDate.now(),
                Type.DRAW,
                UserDto.builder()
                        .lastName(user.getLastName())
                        .middleName(user.getMiddleName())
                        .firstName(user.getFirstName()).build(),
                requestShotDtos, State.NEW);


    }

    @Test
    void add() throws Exception {
        when(declarationServer.add(any()))
                .thenReturn(declarationNew);

        mvc.perform(post("/declaration")
                        .content(mapper.writeValueAsString(declarationDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.user.lastName", is(declarationNew.getUser().getLastName())))
                .andExpect(jsonPath("$.user.firstName", is(declarationNew.getUser().getFirstName())))
                .andExpect(jsonPath("$.user.middleName", is(declarationNew.getUser().getMiddleName())))
                .andExpect(jsonPath("$.state", is(declarationNew.getState().toString())))
                .andExpect(jsonPath("$.type", is(declarationNew.getType().toString())))
                .andExpect(jsonPath("$.createdOn", is(declarationNew.getCreatedOn().format(DTF))))
                .andExpect(jsonPath("$.requests", is(notNullValue())));

        verify(declarationServer, times(1)).add(any());

    }

}