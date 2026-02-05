package com.homie.api.controllers;

import com.homie.api.exception.ResourceNotFoundException;
import com.homie.api.models.Usuario;
import com.homie.api.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String getAll(Model model) {
        model.addAttribute("usuarios", usuarioService.getAll());
        return "usuarios";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.getById(id);
        model.addAttribute("usuarios", usuarioOpt.map(Collections::singletonList).orElse(Collections.emptyList()));
        return "usuarios";
    }

    @DeleteMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado correctamente.");
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el usuario. Puede que tenga datos asociados.");
        }
        return "redirect:/usuarios";
    }

    @PostMapping
    public String create(@ModelAttribute Usuario usuario) {
        usuarioService.create(usuario);
        return "redirect:/usuarios";
    }

    @PutMapping("/actualizar/{id}")
    public String update(
            @PathVariable Long id, RedirectAttributes redirectAttributes,
            @ModelAttribute Usuario usuario) {
        usuarioService.update(id, usuario);
        return "redirect:/usuarios";
    }
}

