package com.zidio.connect.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length=2000)
    private String description;
    private String location;
    private boolean internship;
    private String company;
    private LocalDateTime createdAt;
    @ManyToOne
    private User recruiter;
}
