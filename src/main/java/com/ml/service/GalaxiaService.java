package com.ml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Galaxia;
import com.ml.model.eventos.EventoGalaxia;
import com.ml.repository.EventoGalaxiaRepository;
import com.ml.repository.GalaxiaRepository;

@Service
public class GalaxiaService {

    @Autowired
    private GalaxiaRepository galaxiaRepository;

    @Autowired
    private EventoGalaxiaRepository eventoRepository;

    public void save(final Galaxia galaxia) {
        galaxiaRepository.save(galaxia);
    }

    public EventoGalaxia eventoParaDia(final int dia) {
        return eventoRepository.findByDia(dia);
    }
}
