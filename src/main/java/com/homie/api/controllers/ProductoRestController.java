package com.homie.api.controllers;

import com.homie.api.dto.ProductoRequest;
import com.homie.api.dto.ProductoResponse;
import com.homie.api.mappers.ProductoMapper;
import com.homie.api.models.Producto;
import com.homie.api.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Gestión de Productos", description = "Endpoints para crear, leer, actualizar y eliminar productos.")
public class ProductoRestController {
    private final ProductoService productoService;
    private final ProductoMapper productoMapper;

    public ProductoRestController(ProductoService productoService, ProductoMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos registrados en el sistema.")
    public ResponseEntity<List<ProductoResponse>> getAll() {
        List<Producto> productos = productoService.getAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ProductoResponse> productosResponse = productos.stream()
                .map(productoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productosResponse);
    }

    @Operation(summary = "Obtener producto por id", description = "Devuelve el producto registrados en el sistema que coincide con el id brindado.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> getById(@PathVariable("id") Long id) {
        Optional<Producto> productoOptional = productoService.getById(id);
        if (productoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Producto productoEncontrado = productoOptional.get();
        ProductoResponse productosResponse = productoMapper.toResponse(productoEncontrado);
        return ResponseEntity.ok(productosResponse);

    }

    @Operation(summary = "Borrar producto por id", description = "Borra el producto registrados en el sistema que coincide con el id brindado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crear un producto", description = "Crea un nuevo producto asignandole nombre y presentacion indicada.")
    @PostMapping
    public ResponseEntity<ProductoResponse> create(@RequestBody ProductoRequest nuevoProductoRequest) {
        Producto nuevoProducto = productoMapper.toEntity(nuevoProductoRequest);
        Producto productoGuardado = productoService.create(nuevoProducto);
        ProductoResponse productoResponse = productoMapper.toResponse(productoGuardado);
        return new ResponseEntity<>(productoResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar un producto", description = "Modifica y devuelve un producto.")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductoResponse> update(@PathVariable("id") Long id, @RequestBody ProductoRequest putProductoRequest) {
        Producto productoAModificar = productoMapper.toEntity(putProductoRequest);
        Producto productoModificado = productoService.update(id, productoAModificar);
        ProductoResponse productoResponse = productoMapper.toResponse(productoModificado);
        return ResponseEntity.ok(productoResponse);
    }

}
