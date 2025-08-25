package com.zidio.connect.service;

import com.zidio.connect.model.User;
import com.zidio.connect.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DbUserDetailsService implements UserDetailsService {
    private final UserRepository repo;
    public DbUserDetailsService(UserRepository repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = repo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found"));
        return new org.springframework.security.core.userdetails.User(
                u.getEmail(), u.getPassword(), u.isEnabled(), true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole().name()))
        );
    }
}
