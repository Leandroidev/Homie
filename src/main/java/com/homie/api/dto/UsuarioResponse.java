package com.homie.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"nombre", "apellido", "hogar"})
public class UsuarioResponse {
    private String nombre;
    private String apellido;
    private HogarSimpleResponse hogar;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public HogarSimpleResponse getHogar() {
        return hogar;
    }

    public void setHogar(HogarSimpleResponse hogar) {
        this.hogar = hogar;
    }
}
