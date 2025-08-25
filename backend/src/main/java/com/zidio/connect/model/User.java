package com.zidio.connect.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String resumeUrl; // optional for students
    private boolean enabled = true;
}
