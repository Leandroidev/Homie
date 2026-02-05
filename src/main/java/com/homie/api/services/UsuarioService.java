package com.homie.api.services;

import com.homie.api.data.UsuarioRepository;
import com.homie.api.exception.ResourceNotFoundException;
import com.homie.api.models.Usuario;
import org.springframework.stereotype.Service;

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
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findUsuariosSinHogar() {
        return usuarioRepository.findByHogarIsNull();
    }

    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se pudo eliminar. Usuario no encontrado con id: " + id);
        }
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al borrar el usuario con id: " + id, e);
        }
    }

    public Usuario create(Usuario nuevoUsuario) {
        return usuarioRepository.save(nuevoUsuario);
    }

    public Usuario update(Long id, Usuario putUsuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        usuarioExistente.setNombre(putUsuario.getNombre());
        usuarioExistente.setApellido(putUsuario.getApellido());
        usuarioExistente.setPass(putUsuario.getPass());


        return usuarioRepository.save(usuarioExistente);
    }
}
