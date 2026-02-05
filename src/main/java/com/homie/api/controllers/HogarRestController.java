package com.homie.api.controllers;

import com.homie.api.models.Hogar;
import com.homie.api.services.HogarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hogares")
public class HogarRestController {

    private final HogarService hogarService;

    public HogarRestController(HogarService hogarService) {
        this.hogarService = hogarService;
    }

    @GetMapping
    public List<Hogar> getAllHogares() {
        return hogarService.getAll();
    }

}
