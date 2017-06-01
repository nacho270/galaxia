package com.ml.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ml.model.eventos.EventoGalaxia;
import com.ml.service.GalaxiaService;

/**
 * Interfaz RESTful
 */
@RestController
public class GalaxiaRest {

    @Autowired
    private GalaxiaService service;

    /**
     * Obtiene el evento ocurrido en un dia particular.
     *
     * @param dia
     *            {@link Integer} El dia requerido.
     * @return {@link EventoGalaxia}
     */
    @RequestMapping("/clima")
    public final EventoGalaxia eventoParaDia(@RequestParam final int dia) {
        return service.eventoParaDia(dia);
    }
}
