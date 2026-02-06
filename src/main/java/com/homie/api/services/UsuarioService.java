package com.homie.api.services;

import com.homie.api.data.UsuarioRepository;
import com.homie.api.exception.ResourceNotFoundException;
import com.homie.api.models.Producto;
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
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (putUsuario.getNombre() == null &&
                putUsuario.getApellido() == null &&
                putUsuario.getPass() == null) {
            throw new IllegalArgumentException("La solicitud de actualización no contiene campos para modificar.");
        }
        if (usuarioExistente.isPresent()) {
            Usuario usuarioActualizado = usuarioExistente.get();
            if (putUsuario.getNombre() != null) {
                usuarioActualizado.setNombre(putUsuario.getNombre());
            }
            if (putUsuario.getApellido() != null) {
                usuarioActualizado.setApellido(putUsuario.getApellido());
            }
            if (putUsuario.getPass() != null) {
                usuarioActualizado.setPass(putUsuario.getPass());
            }
            return usuarioRepository.save(usuarioActualizado);
        } else {
            throw new ResourceNotFoundException("No se pudo Actualizar. Producto no encontrado con id: " + id);
        }
    }
}
