package ru.zazhig.getway.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zazhig.getway.request.model.Request;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(" SELECT d " +
            "FROM Request as r " +
            "LEFT JOIN Declaration as d ON r.id.declaration.id = d.id " +
            "LEFT JOIN Resource as res ON r.id.resource.id =res.id " +
            "LEFT JOIN Document AS doc ON d.document.id = doc.id " +
            "LEFT JOIN User AS u ON doc.user.id = u.id " +
            "WHERE (d.state = 'CONFIRMED') and u.id = ?1 and res.district =?2 and res.name = ?3")
    Optional<List<Request>> findByParameters(Long idUser, String district, String nameResource);

    @Query(" SELECT r " +
            "FROM Request as r " +
            "LEFT JOIN Declaration as d ON r.id.declaration.id = d.id " +
            "WHERE d.id = ?1 ")
    Set<Request> findRequestsByDeclarationId(Long declaration_id);
}
