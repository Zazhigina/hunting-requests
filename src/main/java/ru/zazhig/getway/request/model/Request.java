package ru.zazhig.getway.request;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.WhereJoinTable;
import ru.zazhig.getway.declaration.Declaration;
import ru.zazhig.getway.resource.model.Resource;
import ru.zazhig.getway.user.model.User;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "requests", schema = "public")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "declaration_id")
    private Declaration declaration;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;


}
