package ru.zazhig.getway.review;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.zazhig.getway.declaration.Declaration;
import ru.zazhig.getway.declaration.Type;
import ru.zazhig.getway.declaration.dto.DeclarationDto;
import ru.zazhig.getway.declaration.mapper.DeclarationMapper;
import ru.zazhig.getway.declaration.repository.DeclarationRepository;
import ru.zazhig.getway.declaration.server.DeclarationServer;
import ru.zazhig.getway.request.RequestStatus;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.request.model.Request;
import ru.zazhig.getway.request.repository.RequestRepository;
import ru.zazhig.getway.resource.model.BaseResource;
import ru.zazhig.getway.resource.model.Resource;
import ru.zazhig.getway.resource.repository.ResourceBaseRepository;
import ru.zazhig.getway.user.model.Document;
import ru.zazhig.getway.user.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static ru.zazhig.getway.declaration.State.NEW;
import static ru.zazhig.getway.declaration.State.VERIFIED;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private static volatile boolean start = false;
    private static boolean skript = false;
    private final DeclarationRepository declarationRepository;
    private final ResourceBaseRepository resourceBaseRepository;
    private final RequestRepository requestRepository;
    private final DeclarationServer declarationServer;


    public static Random rand = new Random();

    public static List<User> users = List.of(new User(1L, "user1", "last1'", "middle1"),
            new User(2L, "user2", "last2", "middle2"),
            new User(3L, "user3", "last3", "middle3"),
            new User(4L, "user4", "last4", "middle4"),
            new User(5L, "user5", "last5", "middle5"));

    public static List<Document> documents = List.of(new Document(1L, LocalDate.of(2023, 01, 01),
                    11L, 11111111L, users.get(0)),

            new Document(2L, LocalDate.of(2023, 02, 02),
                    22L, 22222222L, users.get(1)),

            new Document(3L, LocalDate.of(2023, 03, 03),
                    33L, 33333333L, users.get(2)),

            new Document(4L, LocalDate.of(2023, 04, 04),
                    44L, 44444444L, users.get(3)),
            new Document(5L, LocalDate.of(2023, 05, 05),
                    55L, 55555555L, users.get(4)));

    public static List<Resource> resources = List.of(new Resource(1L, "гусь", "санкт-петербург"),
            new Resource(2L, "кабан", "санкт-петербург"),
            new Resource(3L, "кролик", "санкт-петербург"),
            new Resource(1L, "лось", "москва"),
            new Resource(2L, "кабан", "москва"),
            new Resource(3L, "кролик", "москва"));


    @PostConstruct
    @Scheduled(fixedDelay = 9000)
    public void review() throws BadRequestException {
        if (!skript) {
            for (int i = 1; i <= 100; i++) {
                int randInt = rand.nextInt(5);
                int randIn1 = rand.nextInt(5);

                while (randInt == randIn1) {
                    randIn1 = rand.nextInt(5);
                }

                DeclarationDto declarationDto = new DeclarationDto(users.get(randInt).getFirstName(),
                        users.get(randInt).getMiddleName(),
                        users.get(randInt).getLastName(),
                        Type.DRAW,
                        documents.get(randInt).getIssueDate(),
                        documents.get(randInt).getSeries(),
                        documents.get(randInt).getNumber(),
                        Set.of(new RequestDto(resources.get(randInt).getDistrict(), resources.get(randInt).getName(), rand.nextLong(15)),
                                new RequestDto(resources.get(randIn1).getDistrict(), resources.get(randIn1).getName(), rand.nextLong(10)))
                );
                declarationServer.add(declarationDto);
            }
            skript = true;
        }
        try {
            while (start) {
                log.info("Началась проверка заявлений");
                doCheck();
                log.info("Закончилась проверка заявлений");
            }
            log.info("Выключили проверку заявлений");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void start() {
        start = true;
    }

    @Override
    public void stop() {
        start = false;
    }

    public void doCheck() {
        List<BaseResource> baseResources = resourceBaseRepository.findAll();
        List<Declaration> decNew = declarationRepository.getAllByStateContaining(NEW);
        DeclarationMapper.toNewState(decNew);
        decNew.stream().map(declarationRepository::save);
        for (Declaration d : decNew) {
            Long idUser = d.getDocument().getUser().getId();
            Set<Request> requests = requestRepository.findRequestsByDeclarationId(d.getId());
            for (Request request : requests) {
                Optional<List<Request>> requestListConf = requestRepository.findByParameters(idUser,// проверка есть ли уже подтвержденные заявки ресурса с одного района
                        request.getId().getResource().getDistrict(),
                        request.getId().getResource().getName());
                if (requestListConf.isPresent()) {
                    if (requestListConf.get().isEmpty()) {
                        BaseResource baseResource = baseResources.get(Math.toIntExact(request.getId().getResource().getId()) - 1);
                        if (d.getCreatedOn().isBefore(baseResource.getEndDate()) &&
                                d.getCreatedOn().isAfter(baseResource.getStartDate())) {
                            if (request.getCount() <= baseResource.getQuota()) {
                                request.setStatus(RequestStatus.CONFIRMED);
                                requestRepository.save(request);
                            }
                        } else {
                            request.setStatus(RequestStatus.REJECTED);
                            requestRepository.save(request);
                        }
                    } else {
                        request.setStatus(RequestStatus.REJECTED);
                        requestRepository.save(request);
                    }
                } else {
                    request.setStatus(RequestStatus.REJECTED);
                    requestRepository.save(request);
                }
            }
            d.setState(VERIFIED);
            declarationRepository.save(d);
        }
    }


}
