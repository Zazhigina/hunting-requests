package ru.zazhig.getway.resource;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users", schema = "public")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_resource")
    private String nameResource;
    @Column(name = "district")
    private String district;
    @Column(name = "quota")
    private Long quota;
}
