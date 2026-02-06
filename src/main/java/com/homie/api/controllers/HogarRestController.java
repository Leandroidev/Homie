package com.homie.api.controllers;

import com.homie.api.dto.HogarRequest;
import com.homie.api.dto.HogarResponse;
import com.homie.api.dto.UsuarioRequest;
import com.homie.api.dto.UsuarioResponse;
import com.homie.api.mappers.HogarMapper;
import com.homie.api.models.Hogar;
import com.homie.api.models.Producto;
import com.homie.api.models.Usuario;
import com.homie.api.services.HogarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hogares")
@Tag(name = "Gestión de hogares", description = "Endpoints para crear, leer, actualizar y eliminar hogares. Permite asignar habitantes a un hogar.")
public class HogarRestController {

    private final HogarService hogarService;
    private final HogarMapper hogarMapper;

    public HogarRestController(HogarService hogarService, HogarMapper hogarMapper) {
        this.hogarService = hogarService;
        this.hogarMapper = hogarMapper;

    }

    @Operation(summary = "Obtener todos los hogares", description = "Devuelve una lista de todos los hogares registrados en el sistema.")
    @GetMapping
    public ResponseEntity<List<HogarResponse>> getAll() {
        List<Hogar> hogares = hogarService.getAll();
        if (hogares.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<HogarResponse> hogaresResponse = hogares.stream()
                .map(hogarMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(hogaresResponse);
    }

    @Operation(summary = "Obtener un hogar por su id", description = "Devuelve el hogar registrados en el sistema que coincide con el id suministrado.")
    @GetMapping("/{id}")
    public ResponseEntity<HogarResponse> getById(@PathVariable("id") Long id) {
        Optional<Hogar> hogarOptional = hogarService.getById(id);
        if (hogarOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Hogar hogarEncontrado = hogarOptional.get();
            HogarResponse hogarResponse = hogarMapper.toResponse(hogarEncontrado);
            return ResponseEntity.ok(hogarResponse);
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
    public ResponseEntity<HogarResponse> create(@RequestBody HogarRequest nuevoHogarRequest) {

        Hogar nuevoHogar = hogarMapper.toEntity(nuevoHogarRequest);
        Hogar hogarCreado = hogarService.create(nuevoHogar);
        HogarResponse hogarResponse = hogarMapper.toResponse(hogarCreado);

        return new ResponseEntity<>(hogarResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un hogar por su id", description = "Modifica un hogar asignandole el nombre suminstrado, devuelve el hogar modificado.")
    @PatchMapping("/{id}")
    public ResponseEntity<HogarResponse> update(@PathVariable("id") Long id, @RequestBody HogarRequest putHogarRequest) {
        Hogar hogarAModificar = hogarMapper.toEntity(putHogarRequest);
        Hogar hogarModificado = hogarService.update(id, hogarAModificar);
        HogarResponse hogarResponse = hogarMapper.toResponse(hogarModificado);
        return ResponseEntity.ok(hogarResponse);
    }

    @Operation(summary = "Asigna habitante a un hogar", description = "Asigna un usuario a un hogar, si el usuario tenia un hogar asignado este cambiara.")
    @PutMapping("/{hogarId}/usuarios/{usuarioId}")
    public ResponseEntity<Void> asignarHabitante(@PathVariable Long hogarId, @PathVariable Long usuarioId) {
        hogarService.asignarHabitante(hogarId, usuarioId);
        return ResponseEntity.ok().build();
    }
}
