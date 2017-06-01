package com.ml.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;
import com.ml.service.GalaxiaService;

@RestController
public class GalaxiaRest {

    @Autowired
    public GalaxiaService service;

    @RequestMapping("/")
    public String index() {
        return "Hola wacho! " + service.hola();
    }

    @RequestMapping("/crear")
    public void crear() {
        final Galaxia galaxia = new Galaxia(new EstrategiaCartesiana());
        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);
        service.crear(galaxia);
    }
}
