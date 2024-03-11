package ru.zazhig.getway.resource;

import ru.zazhig.getway.resource.model.BaseResource;

public interface ResourceServer {
    BaseResource add(ResourceNewDto resourceNewDto);
}
