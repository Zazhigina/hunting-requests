package ru.zazhig.getway.declaration.repository;

import jakarta.persistence.*;
import lombok.*;
import ru.zazhig.getway.declaration.Declaration;
import ru.zazhig.getway.resource.model.Resource;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "declaration_id")
    private Declaration declaration;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

}
