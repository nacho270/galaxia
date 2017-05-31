package com.ml.model.eventos;

import com.ml.model.EventoGalaxia;
import com.ml.model.Galaxia;

// 3) Alineados sin incluir sol
public class AccionCOPT implements AccionEventoGalaxia {

    // determinar si 3 puntos son una recta
    // (x2 - x1) / (x3 - x2) ===== (y2 - y1) / (y3 - y2)

    @Override
    public boolean aplica(final Galaxia galaxia) {
        // boolean esRecta = true;
        // for (int i = 0; i < galaxia.getPlanetas().size() - 2; i++) {
        // esRecta &= false; // (x2 - x1) / (x3 - x2) ===== (y2 - y1) / (y3 - y2)
        // }
        // return esRecta;
        return estanAlineados(galaxia.getPlanetas());
    }

    @Override
    public void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(EventoGalaxia.COPT);
    }
}
