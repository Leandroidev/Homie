package com.homie.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Gestión de Autentificacion", description = "Permite autenticarse en la aplicacion. User: admin PASS: 123")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;

    public AuthenticationRestController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/api/login")
    @Operation(summary = "Ingresa al sistema", description = "Ingresa al sistema." + "\n\n" +
            "Credenciales admin" + "\n\n" + "\t" +
            "User: admin | Pass: 123" + "\n\n" +
            "Credenciales usuario " + "\n\n" + '\t' +
            "User: user | Pass: 321")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication auth = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            return ResponseEntity.ok(Map.of("message", "Login exitoso"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
