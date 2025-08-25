package com.zidio.connect.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="applications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApplicationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) private User student;
    @ManyToOne(optional=false) private Job job;
    private String status; // APPLIED, SHORTLISTED, REJECTED
    private LocalDateTime appliedAt;
}
