package com.ml.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
