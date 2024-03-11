package ru.zazhig.getway.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zazhig.getway.resource.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource,Long> {
}

