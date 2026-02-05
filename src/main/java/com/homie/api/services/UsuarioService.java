package com.homie.api.services;

import com.homie.api.data.UsuarioRepository;
import com.homie.api.models.Hogar;
import com.homie.api.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getById(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findUsuariosSinHogar() {
        return usuarioRepository.findByHogarIsNull();
    }

}
