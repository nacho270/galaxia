package com.ml.repository;

import org.springframework.data.repository.CrudRepository;

import com.ml.model.eventos.EventoGalaxia;

/**
 * Repositorio para guardar y leer {@link EventoGalaxia} de la base de datos.
 */
public interface EventoGalaxiaRepository extends CrudRepository<EventoGalaxia, Integer> {

    /**
     * Busca el evento ocurrido en el dia especificado.
     *
     * @param dia
     *            {@link Integer} El dia requerido.
     * @return {@link EventoGalaxia} El evento del dia solicitado.
     */
    EventoGalaxia findByDia(final int dia);
}
