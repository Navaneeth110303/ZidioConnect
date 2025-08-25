package com.zidio.connect.controller;

import com.zidio.connect.model.*;
import com.zidio.connect.repo.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin
public class ApplicationController {
    private final ApplicationRepository repo;
    private final UserRepository userRepo;
    private final JobRepository jobRepo;

    public ApplicationController(ApplicationRepository repo, UserRepository userRepo, JobRepository jobRepo) {
        this.repo = repo; this.userRepo = userRepo; this.jobRepo = jobRepo;
    }

    @PostMapping("/{jobId}")
    public ResponseEntity<ApplicationEntity> apply(@PathVariable Long jobId, Authentication auth) {
        User student = userRepo.findByEmail(auth.getName()).orElseThrow();
        Job job = jobRepo.findById(jobId).orElseThrow();
        ApplicationEntity a = ApplicationEntity.builder()
                .student(student).job(job)
                .status("APPLIED").appliedAt(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(repo.save(a));
    }

    @GetMapping("/me")
    public List<ApplicationEntity> myApplications(Authentication auth) {
        User student = userRepo.findByEmail(auth.getName()).orElseThrow();
        return repo.findByStudent(student);
    }

    @GetMapping("/job/{jobId}")
    public List<ApplicationEntity> forJob(@PathVariable Long jobId) {
        Job job = jobRepo.findById(jobId).orElseThrow();
        return repo.findByJob(job);
    }

    @PostMapping("/{id}/status/{status}")
    public ResponseEntity<ApplicationEntity> updateStatus(@PathVariable Long id, @PathVariable String status) {
        ApplicationEntity a = repo.findById(id).orElseThrow();
        a.setStatus(status);
        return ResponseEntity.ok(repo.save(a));
    }
}
