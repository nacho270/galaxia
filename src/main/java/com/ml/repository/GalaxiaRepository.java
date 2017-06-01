package com.ml.repository;

import org.springframework.data.repository.CrudRepository;

import com.ml.model.Galaxia;

/**
 * Repositorio para guardar y leer {@link Galaxia} de la base de datos.
 */
public interface GalaxiaRepository extends CrudRepository<Galaxia, Short> {

}
