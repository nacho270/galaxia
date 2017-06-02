package com.ml.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.EstadisticasClima;
import com.ml.model.clima.ClimaGalaxia;
import com.ml.repository.ClimaGalaxiaRepository;
import com.ml.repository.EstadisticasClimaRepository;

/**
 * Servicio facade para acceder a los datos del clima la galaxia.<br>
 * Asume una sola galaxia carga. Todos los datos de los climas registrados
 * pertenecen a ella.
 */
@Service
public class ClimaGalaxiaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClimaGalaxiaService.class);

    @Autowired
    private ClimaGalaxiaRepository climaGalaxiaRepository;

    @Autowired
    private EstadisticasClimaRepository estadisticasClimaRepository;

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
        LOGGER.info("Limpiando datos de clima");
        climaGalaxiaRepository.deleteAll();
        estadisticasClimaRepository.deleteAll();
    }

    /**
     * Obtiene las estadisticas del pronostico de clima hecho.
     *
     * @return {@link EstadisticasClima}
     */
    public EstadisticasClima getEstadisticas() {
        return estadisticasClimaRepository.findAll().iterator().next();
    }

    /**
     * Guarda las estadisticas en la base de datos.
     *
     * @param estadisticas
     *            {@link EstadisticasClima} Las estadisticas a guardar.
     */
    public void save(final EstadisticasClima estadisticas) {
        estadisticasClimaRepository.save(estadisticas);
    }
}
