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
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;
    @Column(name = "series", nullable = false)
    private Long series;
    @Column(name = "number", nullable = false)
    private Long number;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
