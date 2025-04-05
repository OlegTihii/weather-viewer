package org.weather.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Sessions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "userId", nullable = false)
    private long userId;

    @Column(name = "expiresAt")
    private LocalDateTime expiresAt;
}
