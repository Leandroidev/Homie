package com.homie.api.services;

import com.homie.api.dto.ProductoRequest;
import com.homie.api.exception.ResourceNotFoundException;
import com.homie.api.models.Producto;
import com.homie.api.data.ProductoRepository;
import com.homie.api.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getById(Long id) {
        return productoRepository.findById(id);
    }

    public void delete(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se pudo eliminar. Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

    public Producto create(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(Long id, Producto putProducto) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (putProducto.getNombre() == null && putProducto.getPresentacion() == null) {
            throw new IllegalArgumentException("La solicitud de actualización no contiene campos para modificar.");
        }
        if (productoExistente.isPresent()) {
            Producto productoActualizado = productoExistente.get();
            if (putProducto.getNombre() != null) {
                productoActualizado.setNombre(putProducto.getNombre());
            }
            if (putProducto.getPresentacion() != null) {
                productoActualizado.setPresentacion(putProducto.getPresentacion());
            }
            return productoRepository.save(productoActualizado);
        } else {
            throw new ResourceNotFoundException("No se pudo Actualizar. Producto no encontrado con id: " + id);
        }
    }
}