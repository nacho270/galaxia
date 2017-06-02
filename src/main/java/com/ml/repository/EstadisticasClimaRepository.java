package com.ml.repository;

import org.springframework.data.repository.CrudRepository;

import com.ml.model.EstadisticasClima;

/**
 * Repositorio para guardar y leer {@link EstadisticasClima} de la base de
 * datos.
 */
public interface EstadisticasClimaRepository extends CrudRepository<EstadisticasClima, Integer> {

}
