package com.team.pe.voteschool.services;

import com.team.pe.voteschool.config.AuthResponse;
import com.team.pe.voteschool.config.LoginRequest;
import com.team.pe.voteschool.config.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
