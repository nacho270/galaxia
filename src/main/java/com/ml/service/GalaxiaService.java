package com.ml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Galaxia;
import com.ml.repository.GalaxiaRepository;

@Service
public class GalaxiaService {

    @Autowired
    private GalaxiaRepository gala;

    public String hola() {
        return "Soy el service";
    }

    public void crear(final Galaxia galaxia) {
        gala.save(galaxia);
    }
}
