package ru.zazhig.getway.review;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.zazhig.getway.declaration.Declaration;
import ru.zazhig.getway.declaration.mapper.DeclarationMapper;
import ru.zazhig.getway.declaration.repository.DeclarationRepository;
import ru.zazhig.getway.request.RequestStatus;
import ru.zazhig.getway.request.model.Request;
import ru.zazhig.getway.request.repository.RequestRepository;
import ru.zazhig.getway.resource.model.Resource;
import ru.zazhig.getway.resource.model.BaseResource;
import ru.zazhig.getway.resource.repository.ResourceBaseRepository;
import ru.zazhig.getway.resource.repository.ResourceRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.zazhig.getway.declaration.State.NEW;
import static ru.zazhig.getway.declaration.State.VERIFIED;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private Boolean check;
    private final DeclarationRepository declarationRepository;
    private final ResourceBaseRepository resourceBaseRepository;
    private final RequestRepository requestRepository;

    @Override
    @Async
    public void review(Boolean check) {
        this.check = check;
        try {
            while (check) {
                log.info("Началась проверка заявлений");
                doRemind();
                log.info("Закончилась проверка заявлений");
            }
            log.info("Выключили проверку заявлений");
        } catch (Exception e) {
        }
    }

    private void doRemind() {
        List<BaseResource> baseResources = resourceBaseRepository.findAll();
        List<Declaration> decNew = declarationRepository.getAllByStateContaining(NEW);
        decNew.forEach(declaration -> declaration.setRequests(requestRepository.findRequestsByDeclaration_Id(declaration.getId())));
        DeclarationMapper.toNewState(decNew);
        decNew.stream().map(declarationRepository::save);
        for (Declaration d : decNew) {
            Long idUser = d.getDocument().getUser().getId();
            Set<Request> eRequests = d.getRequests();
            for (Request request : eRequests) {
                Optional<List<Request>> requestListConf = requestRepository.findByParameters(idUser,// проверка есть ли уже подтвержденные заявки ресурса с одного района
                        request.getResource().getDistrict(),
                        request.getResource().getName());
                if (requestListConf.isPresent()) {
                    if (requestListConf.get().isEmpty()) {
                        BaseResource baseResource = baseResources.get(Math.toIntExact(request.getResource().getId()) - 1);
                        if (d.getCreatedOn().isBefore(baseResource.getEndDate()) &&
                                d.getCreatedOn().isAfter(baseResource.getStartDate())) {
                            if (request.getCount() <= baseResource.getQuota()) {
                                request.setStatus(RequestStatus.CONFIRMED);
                            }
                        }
                    } else {
                        request.setStatus(RequestStatus.REJECTED);
                    }
                } else {
                    request.setStatus(RequestStatus.REJECTED);
                }
            }
            d.setState(VERIFIED);
            declarationRepository.save(d);
        }


    }
}
