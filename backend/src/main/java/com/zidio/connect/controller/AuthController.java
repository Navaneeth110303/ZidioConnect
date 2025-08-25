package com.zidio.connect.controller;

import com.zidio.connect.dto.AuthDtos;
import com.zidio.connect.model.Role;
import com.zidio.connect.model.User;
import com.zidio.connect.repo.UserRepository;
import com.zidio.connect.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final UserRepository userRepo;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, PasswordEncoder encoder, UserRepository userRepo) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDtos.LoginResponse> login(@RequestBody AuthDtos.LoginRequest req) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.email, req.password));
        User user = userRepo.findByEmail(req.email).orElseThrow();
        var resp = new AuthDtos.LoginResponse();
        resp.email = user.getEmail();
        resp.role = user.getRole().name();
        resp.token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthDtos.RegisterRequest req) {
        User u = User.builder()
                .email(req.email)
                .password(encoder.encode(req.password))
                .fullName(req.fullName)
                .role(Role.valueOf(req.role))
                .enabled(true)
                .build();
        userRepo.save(u);
        return ResponseEntity.ok().build();
    }
}
