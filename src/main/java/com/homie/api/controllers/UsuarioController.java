package com.homie.api.controllers;

import com.homie.api.models.Usuario;
import com.homie.api.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String getAllUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.getAll());
        return "usuarios";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.getById(id);
        model.addAttribute("usuarios", usuarioOpt.map(Collections::singletonList).orElse(Collections.emptyList()));

        return "usuarios";
    }
}

