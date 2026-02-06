package com.homie.api.mappers;

import com.homie.api.dto.ProductoRequest;
import com.homie.api.dto.ProductoResponse;
import com.homie.api.models.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoResponse toResponse(Producto productoResponse) {
        if (productoResponse == null) {
            return null;
        }
        ProductoResponse response = new ProductoResponse();
        response.setNombre(productoResponse.getNombre());
        response.setPresentacion(productoResponse.getPresentacion());
        return response;
    }

    public Producto toEntity(ProductoRequest productoRequest) {
        if (productoRequest == null) {
            return null;
        }
        Producto producto = new Producto();
        producto.setNombre(productoRequest.getNombre());
        producto.setPresentacion(productoRequest.getPresentacion());
        return producto;
    }
}