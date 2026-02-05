package com.homie.api.services;

import com.homie.api.data.HogarRepository;
import com.homie.api.data.UsuarioRepository;
import com.homie.api.exception.ResourceNotFoundException;
import com.homie.api.models.Hogar;
import com.homie.api.models.Producto;
import com.homie.api.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HogarService {
    private final HogarRepository hogarRepository;
    private final UsuarioRepository usuarioRepository;

    public HogarService(HogarRepository hogarRepository, UsuarioRepository usuarioRepository) {
        this.hogarRepository = hogarRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Hogar> getAll() {
        return hogarRepository.findAll();
    }

    public Optional<Hogar> getById(Long id) {
        if (!hogarRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se pudo eliminar. Producto no encontrado con id: " + id);
        }
        return hogarRepository.findById(id);
    }

    public Hogar create(Hogar nuevoHogar) {
        return hogarRepository.save(nuevoHogar);
    }

    public void delete(Long id) {
        if (!hogarRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se pudo eliminar. Producto no encontrado con id: " + id);
        }
        hogarRepository.deleteById(id);
    }

    public Hogar update(Long id, Hogar putHogar) {
        Optional<Hogar> hogarExistente = hogarRepository.findById(id);
        if (hogarExistente.isPresent()) {
            Hogar hogarActualizado = hogarExistente.get();
            hogarActualizado.setNombre(putHogar.getNombre());
            return hogarRepository.save(hogarActualizado);
        } else {
            throw new ResourceNotFoundException("No se pudo Actualizar. Producto no encontrado con id: " + id);
        }
    }

    public void asignarHabitante(Long hogarId, Long usuarioId) {
        Optional<Hogar> hogar = hogarRepository.findById(hogarId);
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent() && hogar.isPresent()) {
            Hogar hogarEncontrado = hogar.get();
            Usuario usuarioEncontrado = usuario.get();
            usuarioEncontrado.setHogar(hogarEncontrado);
            usuarioRepository.save(usuarioEncontrado);
        }
        if (!usuario.isPresent()) {
            throw new ResourceNotFoundException("No se pudo Actualizar. Usuario no encontrado con id: " + usuarioId);
        }
        if (!hogar.isPresent()) {
            throw new ResourceNotFoundException("No se pudo Actualizar. Hogar no encontrado con id: " + usuarioId);
        }
    }


}
