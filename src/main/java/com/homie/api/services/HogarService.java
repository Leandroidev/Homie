package com.homie.api.services;

import com.homie.api.data.HogarRepository;
import com.homie.api.models.Hogar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HogarService {
    private final HogarRepository hogarRepository;


    public HogarService(HogarRepository hogarRepository) {
        this.hogarRepository = hogarRepository;
    }

    public List<Hogar> getAll() {
        return hogarRepository.findAll();
    }

    public Optional<Hogar> getById(Long id) {
        return hogarRepository.findById(id);
    }

    public Hogar guardarNuevoHogar(Hogar hogar) {
        return hogarRepository.save(hogar);
    }
}
