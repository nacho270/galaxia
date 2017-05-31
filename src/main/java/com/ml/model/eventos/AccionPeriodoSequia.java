package com.ml.model.eventos;

import com.ml.model.EventoGalaxia;
import com.ml.model.Galaxia;

// 1) Alineados incluido sol
public class AccionPeriodoSequia implements AccionEventoGalaxia {

    // determinar si 3 puntos son una recta => Comparacion de pendientes
    // (x2 - x1) / (x3 - x2) ===== (y2 - y1) / (y3 - y2)

    @Override
    public boolean aplica(final Galaxia galaxia) {
        return estanAlineados(galaxia.getPlanetas(), galaxia.getSol());
    }

    @Override
    public void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(EventoGalaxia.PERIODO_SEQUIA);
    }
}
