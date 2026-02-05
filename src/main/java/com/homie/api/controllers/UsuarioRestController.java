package com.homie.api.controllers;

import com.homie.api.models.Usuario;
import com.homie.api.services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAll();
    }

}
