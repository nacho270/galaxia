package com.ml.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;
import com.ml.repository.EventoGalaxiaRepository;
import com.ml.service.GalaxiaService;

@Component
public class PronosticoScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PronosticoScheduler.class);

    private static final int UN_ANIO = 365;

    @Autowired
    private GalaxiaService service;

    @Autowired
    private EventoGalaxiaRepository eventoRepository;

    // corre solo una vez
    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void iniciarDatosYHacerPronostico() {
        final Galaxia galaxia = new Galaxia(new EstrategiaCartesiana());
        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);
        service.save(galaxia);

        LOGGER.info("BORRANDO EVENTOS");
        eventoRepository.deleteAll();

        LOGGER.info("GENERANDO PRONOSTICO A 10 AÑOS");
        galaxia.simularHasta(UN_ANIO * 10);
        service.save(galaxia);
        LOGGER.info("PRONOSTICO GENERADO");
    }
}
