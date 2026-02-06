package com.homie.api.mappers;

import com.homie.api.dto.HogarRequest;
import com.homie.api.dto.HogarSimpleResponse;
import com.homie.api.dto.UsuarioRequest;
import com.homie.api.dto.HogarResponse;
import com.homie.api.models.Hogar;
import com.homie.api.models.Usuario;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class HogarMapper {


    public HogarSimpleResponse toSimpleResponse(Hogar hogar) {
        if (hogar == null) {
            return null;
        }
        HogarSimpleResponse simpleResponse = new HogarSimpleResponse();
        simpleResponse.setNombre(hogar.getNombre());
        return simpleResponse;
    }

    public HogarResponse toResponse(Hogar hogarResponse) {
        if (hogarResponse == null) {
            return null;
        }
        HogarResponse response = new HogarResponse();
        response.setNombre(hogarResponse.getNombre());
        if (hogarResponse.getHabitantes() != null) {
            response.setHabitantes(
                    hogarResponse.getHabitantes().stream()
                            .map(UsuarioMapper::toSimpleResponse) //
                            .collect(Collectors.toList())
            );
        } else {
            response.setHabitantes(Collections.emptyList());
        }
        return response;
    }

    public Hogar toEntity(HogarRequest hogarRequest) {
        if (hogarRequest == null) {
            return null;
        }
        Hogar hogar = new Hogar();
        hogar.setNombre(hogarRequest.getNombre());
        return hogar;
    }
}