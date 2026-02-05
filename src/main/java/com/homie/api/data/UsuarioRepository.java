package com.homie.api.data;

import com.homie.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByHogarIsNull();

    boolean existsById(Long id);

    void deleteById(Long id);
}
