package ru.zazhig.getway.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "document", schema = "public")
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "document_issue_date", nullable = false)
    private LocalDate documentIssueDate;
    @Column(name = "series_of_document", nullable = false)
    private Long seriesOfDocuments;
    @Column(name = "number_of_document", nullable = false)
    private Long numberOfDocuments;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
