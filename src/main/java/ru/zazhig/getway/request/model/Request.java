package ru.zazhig.getway.request.model;

import jakarta.persistence.*;
import lombok.*;
import ru.zazhig.getway.declaration.repository.CourseRequestKey;
import ru.zazhig.getway.request.RequestStatus;


@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "requests", schema = "public")
public class Request {

    @EmbeddedId
    private CourseRequestKey id;

    @Column(name = "count")
    @Builder.Default
    private Long count = 0L;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.NEW;


}
