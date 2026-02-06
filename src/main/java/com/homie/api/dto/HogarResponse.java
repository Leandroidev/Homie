package com.homie.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"nombre", "habitantes"})

public class HogarResponse {
    private String nombre;
    private List<HabitanteSimpleResponse> habitantes;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<HabitanteSimpleResponse> getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(List<HabitanteSimpleResponse> habitantes) {
        this.habitantes = habitantes;
    }
}
