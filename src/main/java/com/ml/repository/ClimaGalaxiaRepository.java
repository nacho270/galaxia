package com.ml.repository;

import org.springframework.data.repository.CrudRepository;

import com.ml.model.clima.ClimaGalaxia;

/**
 * Repositorio para guardar y leer {@link ClimaGalaxia} de la base de datos.
 */
public interface ClimaGalaxiaRepository extends CrudRepository<ClimaGalaxia, Integer> {

    /**
     * Busca el clima ocurrido en el dia especificado.
     *
     * @param dia
     *            {@link Integer} El dia requerido.
     * @return {@link ClimaGalaxia} El clima del dia solicitado.
     */
    ClimaGalaxia findByDia(final Integer dia);
}
