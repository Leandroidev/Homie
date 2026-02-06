package com.homie.api.controllers;

import com.homie.api.dto.UsuarioRequest;
import com.homie.api.dto.UsuarioResponse;
import com.homie.api.mappers.UsuarioMapper;
import com.homie.api.models.Usuario;
import com.homie.api.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Gestión de Usuarios", description = "Endpoints para crear, leer, actualizar y eliminar usuarios.")
public class UsuarioRestController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRestController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados en el sistema.")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getAll() {
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UsuarioResponse> usuariosResponse = usuarios.stream()
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosResponse);
    }

    @Operation(summary = "Obtener usuario por id", description = "Devuelve el usuario registrados en el sistema que coincide con el id brindado.")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getById(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getById(id);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Usuario usuarioEncontrado = usuarioOptional.get();
            UsuarioResponse usuarioResponse = usuarioMapper.toResponse(usuarioEncontrado);
            return ResponseEntity.ok(usuarioResponse);

        }
    }

    @Operation(summary = "Borrar usuario por id", description = "Borra el usuario registrados en el sistema que coincide con el id brindado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Crear un usuario", description = "Crea un nuevo usuario asignandole nombre, apellido y pass.")
    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@RequestBody UsuarioRequest nuevoUsuarioRequest) {

        Usuario nuevoUsuario = usuarioMapper.toEntity(nuevoUsuarioRequest);
        Usuario usuarioCreado = usuarioService.create(nuevoUsuario);
        UsuarioResponse usuarioResponse = usuarioMapper.toResponse(usuarioCreado);

        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar un usuario", description = "Modifica y devuelve un usuario.")
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable("id") Long id, @RequestBody UsuarioRequest putUsuarioRequest) {
        Usuario usuarioAModificar = usuarioMapper.toEntity(putUsuarioRequest);
        Usuario usuarioModificado = usuarioService.update(id, usuarioAModificar);
        UsuarioResponse usuarioResponse = usuarioMapper.toResponse(usuarioModificado);
        return ResponseEntity.ok(usuarioResponse);
    }
}
