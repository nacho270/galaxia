package com.ml.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ml.model.EstadisticasClima;
import com.ml.model.Galaxia;
import com.ml.model.SimuladorClima;
import com.ml.model.posicionamiento.cartesiano.CalculadorPosicionCartesiana;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;
import com.ml.service.ClimaGalaxiaService;
import com.ml.service.GalaxiaService;

/**
 * Job que corre cuando se inicia la aplicacion.
 */
@Component
public class PronosticoScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PronosticoScheduler.class);

    private static final int UN_ANIO = 365;

    @Autowired
    private GalaxiaService galaxiaService;

    @Autowired
    private ClimaGalaxiaService climaGlaxiaService;

    /**
     * Limpia los datos que se encuentra guardados, realiza la carga de la
     * galaxia pedida y simula su comportamiento climatico a 10 años. Corre solo
     * una vez.
     */
    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public final void iniciarDatosYHacerPronostico() {
        LOGGER.info("BORRANDO DATOS DE GALAXIA");
        galaxiaService.limpiarDatos();

        LOGGER.info("BORRANDO DATOS DEL CLIMA");
        climaGlaxiaService.limpiarDatos();

        final Galaxia galaxia = new Galaxia(new CalculadorPosicionCartesiana());
        galaxia.agregarPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.agregarPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.agregarPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);
        galaxiaService.save(galaxia);

        LOGGER.info("GENERANDO PRONOSTICO A 10 AÑOS");
        final EstadisticasClima estadisticas = new SimuladorClima(galaxia, new EstrategiaCartesiana()).simularHasta(UN_ANIO * 10);
        climaGlaxiaService.save(estadisticas);
        LOGGER.info("PRONOSTICO GENERADO");
    }
}
