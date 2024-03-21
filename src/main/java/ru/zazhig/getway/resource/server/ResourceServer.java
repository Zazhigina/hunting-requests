package ru.zazhig.getway.resource.server;

import ru.zazhig.getway.resource.dto.ResourceNewDto;
import ru.zazhig.getway.resource.model.BaseResource;

public interface ResourceServer {
    BaseResource add(ResourceNewDto resourceNewDto);
}
