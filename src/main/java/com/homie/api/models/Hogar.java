package com.homie.api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hogar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;

    @OneToMany(mappedBy = "hogar")
    @JsonManagedReference
    private List<Usuario> habitantes;

    @OneToMany(mappedBy = "hogar")
    @JsonManagedReference
    private List<Stock> stock;

    public Hogar() {

    }

    public Hogar(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(List<Usuario> habitantes) {
        this.habitantes = habitantes;
    }

    public List<Stock> getStock() {
        return stock;
    }

    public void setStock(List<Stock> stock) {
        this.stock = stock;
    }
}
