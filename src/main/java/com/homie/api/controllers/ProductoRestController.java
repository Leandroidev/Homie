package com.homie.api.controllers;

import com.homie.api.models.Producto;
import com.homie.api.models.Usuario;
import com.homie.api.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Gestión de Productos", description = "Endpoints para crear, leer, actualizar y eliminar productos.")
public class ProductoRestController {
    private final ProductoService productoService;

    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos registrados en el sistema.")
    public ResponseEntity<List<Producto>> getAll() {
        List<Producto> productos = productoService.getAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @Operation(summary = "Obtener producto por id", description = "Devuelve el producto registrados en el sistema que coincide con el id brindado.")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") Long id) {
        Optional<Producto> productoOptional = productoService.getById(id);
        if (productoOptional.isPresent()) {
            Producto productoEncontrado = productoOptional.get();
            return ResponseEntity.ok(productoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Borrar producto por id", description = "Borra el producto registrados en el sistema que coincide con el id brindado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crear un producto", description = "Crea un nuevo producto asignandole nombre y presentacion indicada.")
    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto nuevoProducto) {
        Producto productoGuardado = productoService.create(nuevoProducto);
        return new ResponseEntity<>(productoGuardado, HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar un producto", description = "Modifica y devuelve un producto.")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable("id") Long id, @RequestBody Producto putProducto) {
        Producto productoModificado = productoService.update(id, putProducto);
        return ResponseEntity.ok(productoModificado);
    }

}
