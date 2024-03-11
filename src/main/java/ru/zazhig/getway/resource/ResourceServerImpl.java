package ru.zazhig.getway.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zazhig.getway.resource.model.BaseResource;
import ru.zazhig.getway.resource.model.Resource;
import ru.zazhig.getway.resource.repository.ResourceBaseRepository;
import ru.zazhig.getway.resource.repository.ResourceRepository;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResourceServerImpl implements ResourceServer {

    private final ResourceBaseRepository resourceBaseRepository;
    private final ResourceRepository resourceRepository;

    @Override

    public BaseResource add(ResourceNewDto resourceNewDto) {
        Resource resource = resourceRepository.save(ResourceMapper.toResource(resourceNewDto));
        return resourceBaseRepository.save(ResourceMapper.toResourceBase(resourceNewDto, resource));
    }
}
