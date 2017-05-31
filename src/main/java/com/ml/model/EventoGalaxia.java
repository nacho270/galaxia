package com.ml.model;

import com.ml.model.eventos.AccionCOPT;
import com.ml.model.eventos.AccionEventoGalaxia;
import com.ml.model.eventos.AccionPeriodoLluvia;
import com.ml.model.eventos.AccionPeriodoSequia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

public enum EventoGalaxia {
    PERIODO_SEQUIA(new AccionPeriodoSequia()), //
    PERIODO_LLUVIA(new AccionPeriodoLluvia()), //
    COPT(new AccionCOPT()); // condiciones optimas de de presion y temperatura

    EventoGalaxia(final AccionEventoGalaxia accionEvento) {
        this.accionEvento = accionEvento;
    }

    private final AccionEventoGalaxia accionEvento;

    public boolean aplica(final Galaxia galaxia, CalculadorPosicion<?> calculadorPosicion) {
        return accionEvento.aplica(galaxia, calculadorPosicion);
    }

    public void computar(final Galaxia galaxia) {
        accionEvento.computar(galaxia);
    }
}
