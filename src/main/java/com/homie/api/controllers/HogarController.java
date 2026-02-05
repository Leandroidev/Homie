package com.homie.api.controllers;

import com.homie.api.models.Hogar;
import com.homie.api.services.HogarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;


@Controller
@RequestMapping("/hogares")
public class HogarController {

    private final HogarService hogarService;

    public HogarController(HogarService hogarService) {
        this.hogarService = hogarService;
    }

    @GetMapping
    public String getAllHogares(Model model) {
        model.addAttribute("hogares", hogarService.getAll());
        return "hogares";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Optional<Hogar> hogarOpt = hogarService.getById(id);
        model.addAttribute("hogares", hogarOpt.map(Collections::singletonList).orElse(Collections.emptyList()));

        return "hogares";
    }

    @PostMapping
    public String createHogar(@ModelAttribute Hogar hogar) {
        hogarService.create(hogar);
        return "redirect:/hogares";
    }
}