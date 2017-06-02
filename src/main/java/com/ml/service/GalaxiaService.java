package com.ml.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.ClimaCantidad;
import com.ml.model.EstadisticasClima;
import com.ml.model.Galaxia;
import com.ml.repository.GalaxiaRepository;

/**
 * Servicio facade para acceder a los datos de la galaxia.
 */
@Service
public class GalaxiaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GalaxiaService.class);

    @Autowired
    private GalaxiaRepository galaxiaRepository;

    /**
     * Guarda la galaxia en la base de datos.
     *
     * @param galaxia
     *            {@link Galaxia} La galaxia a guardar.
     */
    public void save(final Galaxia galaxia) {
        LOGGER.info("Grabando galaxia");
        galaxiaRepository.save(galaxia);
    }

    /**
     * Limpia los datos de la galaxia.
     */
    public void limpiarDatos() {
        galaxiaRepository.deleteAll();
    }

    /**
     * Obtiene las estadisticas del pronostico de clima hecho.
     *
     * @return {@link EstadisticasClima}
     */
    public EstadisticasClima getEstadisticas() {
        final Galaxia galaxia = galaxiaRepository.findAll().iterator().next();
        galaxia.getClimas().size(); // para evitar el lazy
        final List<ClimaCantidad> climaCantidad = galaxia.getClimas().stream() //
                        .collect(Collectors.groupingBy(clima -> clima.getClima(), Collectors.counting())) //
                        .entrySet().stream() //
                        .map(entry -> new ClimaCantidad(entry.getKey(), entry.getValue().intValue())) //
                        .collect(Collectors.toList());

        return new EstadisticasClima(galaxia.getDiaPicoMaximoLluvia(), climaCantidad);
    }
}
