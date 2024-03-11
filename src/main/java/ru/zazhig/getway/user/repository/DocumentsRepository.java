package ru.zazhig.getway.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zazhig.getway.user.model.Document;

public interface DocumentsRepository extends JpaRepository<Document, Long> {
}
