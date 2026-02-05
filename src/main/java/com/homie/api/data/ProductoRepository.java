package com.homie.api.data;

import com.homie.api.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
