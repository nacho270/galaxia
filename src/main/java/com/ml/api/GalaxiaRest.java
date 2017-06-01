package com.ml.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ml.model.eventos.EventoGalaxia;
import com.ml.service.GalaxiaService;

@RestController
public class GalaxiaRest {

    @Autowired
    public GalaxiaService service;

    @RequestMapping("/clima")
    public EventoGalaxia index(@RequestParam final int dia) {
        return service.eventoParaDia(dia);
    }
}
