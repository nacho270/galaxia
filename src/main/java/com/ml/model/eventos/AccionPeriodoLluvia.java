package com.ml.model.eventos;

import com.ml.model.EventoGalaxia;
import com.ml.model.Galaxia;

//2) Forman triangulo incluido sol
public class AccionPeriodoLluvia implements AccionEventoGalaxia {

    @Override
    public boolean aplica(final Galaxia galaxia) {
        if (!estanAlineados(galaxia.getPlanetas())) {

        }
        return false;
    }

    @Override
    public void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(EventoGalaxia.PERIODO_LLUVIA);
        galaxia.computarPerimetro(perimetro(galaxia));
    }

    private double perimetro(final Galaxia galaxia) {
        return 0;
    }
    // calcular perimetro -> suma de longitud de lados
}
