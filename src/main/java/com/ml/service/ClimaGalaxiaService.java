package com.ml.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.clima.ClimaGalaxia;
import com.ml.repository.ClimaGalaxiaRepository;

/**
 * Servicio facade para acceder a los datos del clima la galaxia.
 */
@Service
public class ClimaGalaxiaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClimaGalaxiaService.class);

    @Autowired
    private ClimaGalaxiaRepository climaGalaxiaRepository;

    /**
     * Obtiene el clima que hubo en un dia en particular.
     *
     * @param dia
     *            {@link Integer} El dia requerido.
     * @return {@link ClimaGalaxia} El clima del dia solicitado.
     */
    public ClimaGalaxia climaParaDia(final int dia) {
        LOGGER.info("Buscando por dia {}", dia);
        return climaGalaxiaRepository.findByDia(dia);
    }

    /**
     * Borra todos los registros del clima.
     */
    public void limpiarDatos() {
        climaGalaxiaRepository.deleteAll();
    }
}
