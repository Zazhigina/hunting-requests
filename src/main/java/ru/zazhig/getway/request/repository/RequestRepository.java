package ru.zazhig.getway.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.zazhig.getway.request.model.Request;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(" SELECT d " +
            "FROM Request as r " +
            "LEFT JOIN Declaration as d ON r.declaration.id = d.id " +
            "LEFT JOIN Resource as res ON r.resource.id = d.id " +
            "LEFT JOIN Document AS doc ON d.document.id = doc.id " +
            "LEFT JOIN User AS u ON doc.user.id = u.id " +
            "WHERE (d.state = 'CONFIRMED') and u.id = ?1 and res.district =?2 and res.name = ?3")
    Optional<List<Request>> findByParameters(Long idUser, String district, String nameResource);


    Set<Request> findRequestsByDeclaration_Id(Long declaration_id);
}
