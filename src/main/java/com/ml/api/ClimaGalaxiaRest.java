package com.ml.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ml.model.EstadisticasClima;
import com.ml.model.clima.ClimaGalaxia;
import com.ml.service.ClimaGalaxiaService;
import com.ml.service.GalaxiaService;

/**
 * Interfaz RESTful para el clima.
 */
@RestController
public class ClimaGalaxiaRest {

    @Autowired
    private ClimaGalaxiaService climaGalaxiaService;

    @Autowired
    private GalaxiaService galaxiaService;

    /**
     * Obtiene el clima ocurrido en un dia particular.
     *
     * @param dia
     *            {@link Integer} El dia requerido.
     * @return {@link ClimaGalaxia}
     */
    @RequestMapping("/clima")
    public final ClimaGalaxia climaParaDia(@RequestParam final int dia) {
        return climaGalaxiaService.climaParaDia(dia);
    }

    /**
     * Obtiene las estadisticas del pronostico de clima hecho.
     *
     * @return {@link EstadisticasClima}
     */
    @RequestMapping("/estadisticas")
    public final EstadisticasClima estadisticas() {
        return galaxiaService.getEstadisticas();
    }
}
