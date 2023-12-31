package com.team.pe.voteschool.controllers;

import com.team.pe.voteschool.config.AuthResponse;
import com.team.pe.voteschool.config.LoginRequest;
import com.team.pe.voteschool.config.RegisterRequest;
import com.team.pe.voteschool.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public Map<String, String> root() {
        Map<String, String> root = new HashMap<>();
        root.put("message", "it works! v1.0");
        return root;
    }

    @PostMapping("auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        log.info(request.getUsername());
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("auth/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        log.info(request.getUsername());
        return ResponseEntity.ok(authService.register(request));
    }
}
