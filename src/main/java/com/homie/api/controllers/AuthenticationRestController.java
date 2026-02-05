package com.homie.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;

    public AuthenticationRestController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication auth = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            // Si es exitoso, puedes devolver un token o mensaje
            return ResponseEntity.ok(Map.of("message", "Login exitoso"));        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
