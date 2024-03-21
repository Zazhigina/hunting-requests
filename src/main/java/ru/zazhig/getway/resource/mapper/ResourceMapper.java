package ru.zazhig.getway.resource.mapper;

import lombok.experimental.UtilityClass;
import ru.zazhig.getway.request.dto.RequestDto;
import ru.zazhig.getway.resource.dto.ResourceNewDto;
import ru.zazhig.getway.resource.model.Resource;
import ru.zazhig.getway.resource.model.BaseResource;


@UtilityClass
public class ResourceMapper {
    public Resource toResource(RequestDto requestDto) {
        return Resource.builder()
                .name(requestDto.getName())
                .district(requestDto.getDistrict())
                .build();
    }


    BaseResource toResourceBase(ResourceNewDto resourceNewDto, Resource resource) {
        return BaseResource.builder()
                .resource(resource)
                .quota(resourceNewDto.getQuota())
                .startDate(resourceNewDto.getStartDate())
                .endDate(resourceNewDto.getEndDate())
                .build();
    }

    public static Resource toResource(ResourceNewDto resourceNewDto) {
        return Resource.builder()
                .name(resourceNewDto.getName())
                .district(resourceNewDto.getDistrict())
                .build();
    }

}
