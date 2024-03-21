package ru.zazhig.getway.request.mapper;

import lombok.experimental.UtilityClass;
import ru.zazhig.getway.declaration.model.Declaration;
import ru.zazhig.getway.declaration.repository.CourseRequestKey;
import ru.zazhig.getway.request.dto.RequestShotDto;
import ru.zazhig.getway.request.model.Request;
import ru.zazhig.getway.request.model.RequestStatus;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.resource.model.Resource;

@UtilityClass
public class RequestMapper {
    public Request toRequestNew(Declaration declaration, Resource resource, RequestDto requestDto) {
        return Request.builder().id(CourseRequestKey.builder()
                        .declaration(declaration)
                        .resource(resource)
                        .build())
                .count(requestDto.getCount())
                .status(RequestStatus.NEW)
                .build();
    }

    public RequestShotDto toRequestShotDto(Request request) {
        return RequestShotDto.builder()
                .district(request.getId().getResource().getDistrict())
                .name(request.getId().getResource().getName())
                .count(request.getCount())
                .status(request.getStatus())
                .build();
    }
}
