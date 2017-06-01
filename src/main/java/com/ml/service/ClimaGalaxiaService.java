package com.ml.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.eventos.ClimaGalaxia;
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
     * Obtiene el evento ocurrido en un dia en particular.
     *
     * @param dia
     *            {@link Integer} El dia requerido.
     * @return {@link ClimaGalaxia} El evento del dia solicitado.
     */
    public ClimaGalaxia eventoParaDia(final int dia) {
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