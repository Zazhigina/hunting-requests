package ru.zazhig.getway.declaration;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.zazhig.getway.request.model.Request;
import ru.zazhig.getway.user.model.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "declaration", schema = "public")
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_on", nullable = false)
    @CreationTimestamp
    private LocalDate createdOn;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    @ManyToMany
    @JoinTable(
            name = "requests",
            joinColumns = @JoinColumn(name = "declaration_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<Request> requests =new HashSet<>();

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;
}
