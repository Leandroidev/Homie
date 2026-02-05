package com.homie.api.services;

import com.homie.api.models.Producto;
import com.homie.api.data.ProductoRepository;
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
}