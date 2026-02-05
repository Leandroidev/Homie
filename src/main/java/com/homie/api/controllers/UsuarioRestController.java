package com.homie.api.controllers;

import com.homie.api.models.Usuario;
import com.homie.api.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Gestión de Usuarios", description = "Endpoints para crear, leer, actualizar y eliminar usuarios.")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados en el sistema.")
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuario por id", description = "Devuelve el usuario registrados en el sistema que coincide con el id brindado.")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioEncontrado = usuarioOptional.get();
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.notFound().build();

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
    public ResponseEntity<Usuario> create(@RequestBody Usuario nuevoUsuario) {
        Usuario usuarioGuardado = usuarioService.create(nuevoUsuario);
        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar un usuario", description = "Modifica y devuelve un usuario.")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id, @RequestBody Usuario putUsuario) {
        Usuario usuarioModificado = usuarioService.update(id, putUsuario);
        return ResponseEntity.ok(usuarioModificado);
    }
}
