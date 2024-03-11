package ru.zazhig.getway.resource;

import org.apache.tomcat.util.descriptor.web.ResourceBase;
import ru.zazhig.getway.resource.model.BaseResource;

import java.time.LocalDate;

public interface ResourceServer {
    BaseResource add(ResourceNewDto resourceNewDto);
}
