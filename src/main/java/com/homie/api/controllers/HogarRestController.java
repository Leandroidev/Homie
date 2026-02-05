package com.homie.api.controllers;

import com.homie.api.models.Hogar;
import com.homie.api.models.Producto;
import com.homie.api.services.HogarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hogares")
@Tag(name = "Gestión de hogares", description = "Endpoints para crear, leer, actualizar y eliminar hogares. Permite asignar habitantes a un hogar.")
public class HogarRestController {

    private final HogarService hogarService;

    public HogarRestController(HogarService hogarService) {
        this.hogarService = hogarService;
    }

    @Operation(summary = "Obtener todos los hogares", description = "Devuelve una lista de todos los hogares registrados en el sistema.")
    @GetMapping
    public ResponseEntity<List<Hogar>> getAll() {
        List<Hogar> hogares = hogarService.getAll();
        if (hogares.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(hogares);
    }

    @Operation(summary = "Obtener un hogar por su id", description = "Devuelve el hogar registrados en el sistema que coincide con el id suministrado.")
    @GetMapping("/{id}")
    public ResponseEntity<Hogar> getById(@PathVariable("id") Long id) {
        Optional<Hogar> hogarOptional = hogarService.getById(id);
        if (hogarOptional.isPresent()) {
            Hogar hogarEncontrado = hogarOptional.get();
            return ResponseEntity.ok(hogarEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Borrar un hogar por su id", description = "Borra el hogar registrado en el sistema que coincide con el id suministrado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        hogarService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crear un hogar ", description = "Crea un hogar asignandole el nombre suminstrado, devuelve el hogar creado.")
    @PostMapping
    public ResponseEntity<Hogar> create(@RequestBody Hogar nuevoHogar) {
        Hogar hogarCreado = hogarService.create(nuevoHogar);
        return new ResponseEntity<>(hogarCreado, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un hogar por su id", description = "Modifica un hogar asignandole el nombre suminstrado, devuelve el hogar modificado.")
    @PutMapping("/{id}")
    public ResponseEntity<Hogar> update(@PathVariable("id") Long id, @RequestBody Hogar putHogar) {
        Hogar hogarModificado = hogarService.update(id, putHogar);
        return ResponseEntity.ok(hogarModificado);
    }

    @Operation(summary = "Asigna habitante a un hogar", description = "Asigna un usuario a un hogar, si el usuario tenia un hogar asignado este cambiara.")
    @PutMapping("/{hogarId}/usuarios/{usuarioId}")
    public ResponseEntity<Void> asignarHabitante(@PathVariable Long hogarId, @PathVariable Long usuarioId) {
        hogarService.asignarHabitante(hogarId, usuarioId);
        return ResponseEntity.ok().build();
    }
}
