package ru.zazhig.getway.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zazhig.getway.resource.model.BaseResource;

import java.util.List;

@Repository
public interface ResourceBaseRepository extends JpaRepository<BaseResource, Long> {
    @Query(value = "SELECT r FROM BaseResource AS r " +
            "JOIN Resource AS res ON r.resource.id = res.id")
    List<BaseResource> findBaseResources();
}
