package ru.zazhig.getway.user.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    @Column(name = "middle_name", length = 50, nullable = false)
    private String middleName;
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

}
