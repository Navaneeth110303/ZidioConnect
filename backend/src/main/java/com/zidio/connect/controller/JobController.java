package com.zidio.connect.controller;

import com.zidio.connect.model.Job;
import com.zidio.connect.model.User;
import com.zidio.connect.repo.JobRepository;
import com.zidio.connect.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin
public class JobController {
    private final JobRepository repo;
    private final UserRepository userRepo;

    public JobController(JobRepository repo, UserRepository userRepo) {
        this.repo = repo; this.userRepo = userRepo;
    }

    @GetMapping
    public List<Job> all() { return repo.findAll(); }

    @PostMapping
    public ResponseEntity<Job> create(@RequestBody Job job, Authentication auth) {
        User recruiter = userRepo.findByEmail(auth.getName()).orElseThrow();
        job.setRecruiter(recruiter);
        job.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(repo.save(job));
    }
}
