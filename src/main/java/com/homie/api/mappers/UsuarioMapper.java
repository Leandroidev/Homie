package com.homie.api.mappers;

import com.homie.api.dto.HabitanteSimpleResponse;
import com.homie.api.dto.HogarSimpleResponse;
import com.homie.api.dto.UsuarioRequest;
import com.homie.api.dto.UsuarioResponse;
import com.homie.api.models.Hogar;
import com.homie.api.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    private final HogarMapper hogarMapper;

    public UsuarioMapper(HogarMapper hogarMapper) {
        this.hogarMapper = hogarMapper;
    }

    public static HabitanteSimpleResponse toSimpleResponse(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        HabitanteSimpleResponse simpleResponse = new HabitanteSimpleResponse();
        simpleResponse.setNombre(usuario.getNombre());
        simpleResponse.setApellido(usuario.getApellido());
        return simpleResponse;
    }

    public UsuarioResponse toResponse(Usuario usuarioResponse) {
        if (usuarioResponse == null) {
            return null;
        }
        UsuarioResponse response = new UsuarioResponse();
        response.setNombre(usuarioResponse.getNombre());
        response.setApellido(usuarioResponse.getApellido());
        response.setHogar(hogarMapper.toSimpleResponse(usuarioResponse.getHogar()));
        return response;
    }

    public Usuario toEntity(UsuarioRequest usuarioRequest) {
        if (usuarioRequest == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setApellido(usuarioRequest.getApellido());
        usuario.setPass(usuarioRequest.getPass());

        return usuario;
    }
}