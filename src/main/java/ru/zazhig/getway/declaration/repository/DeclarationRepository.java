package ru.zazhig.getway.declaration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zazhig.getway.declaration.model.Declaration;
import ru.zazhig.getway.declaration.model.State;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    @Query(value = "SELECT d FROM Declaration AS d LEFT JOIN Request as r ON d.id = r.id.declaration.id " +
            "LEFT JOIN Resource AS res ON r.id.resource.id = res.id " +
            "LEFT JOIN Document AS doc ON d.document.id = doc.id " +
            "LEFT JOIN User AS u ON doc.user.id = u.id " +
            " WHERE d.state = ?1")
    List<Declaration> getAllByStateContaining(State state);

    @Query(value = "SELECT d FROM Declaration AS d LEFT JOIN Request as r ON d.id = r.id.declaration.id " +
            "LEFT JOIN Resource AS res ON r.id.resource.id = res.id " +
            "LEFT JOIN Document AS doc ON d.document.id = doc.id " +
            "LEFT JOIN User AS u ON doc.user.id = u.id " +
            " WHERE d.state = ?1 AND u.id = ?2 ")
    Optional<List<Declaration>> getAllByStateAndDocument_User(State state, Long userId);
}
