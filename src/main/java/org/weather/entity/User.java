package org.weather.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column (name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;
}
