package ru.zazhig.getway.request.mapper;

import lombok.experimental.UtilityClass;
import ru.zazhig.getway.declaration.Declaration;
import ru.zazhig.getway.request.dto.RequestShotDto;
import ru.zazhig.getway.request.model.Request;
import ru.zazhig.getway.request.RequestStatus;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.resource.model.Resource;

@UtilityClass
public class RequestMapper {
    public Request toRequestNew(Declaration declaration, Resource resource, RequestDto requestDto) {
        return Request.builder()
                .id(resource.getId())
                .declaration(declaration)
                .resource(resource)
                .count(requestDto.getCount())
                .status(RequestStatus.REVIEW)
                .build();
    }

    public RequestShotDto toRequestDto(Request request) {
        return RequestShotDto.builder()
                .district(request.getResource().getDistrict())
                .name(request.getResource().getName())
                .count(request.getCount())
                .status(request.getStatus())
                .build();
    }
}
