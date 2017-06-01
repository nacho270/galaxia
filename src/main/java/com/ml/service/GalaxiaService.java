package com.ml.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Galaxia;
import com.ml.model.eventos.EventoGalaxia;
import com.ml.repository.EventoGalaxiaRepository;
import com.ml.repository.GalaxiaRepository;

@Service
public class GalaxiaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GalaxiaService.class);

    @Autowired
    private GalaxiaRepository galaxiaRepository;

    @Autowired
    private EventoGalaxiaRepository eventoRepository;

    public void save(final Galaxia galaxia) {
        LOGGER.info("Grabando galaxia");
        galaxiaRepository.save(galaxia);
    }

    public EventoGalaxia eventoParaDia(final int dia) {
        LOGGER.info("Buscando por dia {}", dia);
        return eventoRepository.findByDia(dia);
    }
}
