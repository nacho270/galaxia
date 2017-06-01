package com.ml.repository;

import org.springframework.data.repository.CrudRepository;

import com.ml.model.eventos.ClimaGalaxia;

/**
 * Repositorio para guardar y leer {@link ClimaGalaxia} de la base de datos.
 */
public interface ClimaGalaxiaRepository extends CrudRepository<ClimaGalaxia, Integer> {

    /**
     * Busca el evento ocurrido en el dia especificado.
     *
     * @param dia
     *            {@link Integer} El dia requerido.
     * @return {@link ClimaGalaxia} El evento del dia solicitado.
     */
    ClimaGalaxia findByDia(final int dia);
}
