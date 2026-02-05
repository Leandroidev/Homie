package com.homie.api.controllers;

import com.homie.api.models.Producto;
import com.homie.api.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("productos", productoService.getAll());
        return "productos";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Optional<Producto> productOpt = productoService.getById(id);
        model.addAttribute("productos", productOpt.map(Collections::singletonList).orElse(Collections.emptyList()));

        return "productos";
    }
}
