package com.ml.repository;

import org.springframework.data.repository.CrudRepository;

import com.ml.model.eventos.EventoGalaxia;

public interface EventoGalaxiaRepository extends CrudRepository<EventoGalaxia, Integer> {

    EventoGalaxia findByDia(final int dia);
}
