package com.team.pe.voteschool.services.impl;

import com.team.pe.voteschool.config.AuthResponse;
import com.team.pe.voteschool.config.LoginRequest;
import com.team.pe.voteschool.config.RegisterRequest;
import com.team.pe.voteschool.config.jwt.JwtService;
import com.team.pe.voteschool.models.User;
import com.team.pe.voteschool.repository.UserRepository;
import com.team.pe.voteschool.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(user);
            log.info(token);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getUsername())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .Id(UUID.randomUUID().toString())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .category(request.getCategory())
                .documentNumber(request.getDocumentNumber())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
