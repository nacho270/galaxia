package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

public enum TipoEventoGalaxia {
    PERIODO_SEQUIA(new AccionPeriodoSequia()), //
    PERIODO_LLUVIA(new AccionPeriodoLluvia()), //
    COPT(new AccionCOPT()), // condiciones optimas de de presion y temperatura
    NORMAL(new AccionDiaNormal());

    TipoEventoGalaxia(final AccionEventoGalaxia accionEvento) {
        this.accionEvento = accionEvento;
    }

    private final AccionEventoGalaxia accionEvento;

    public boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return accionEvento.aplica(galaxia, calculadorPosicion);
    }

    public void computar(final Galaxia galaxia) {
        accionEvento.computar(galaxia);
    }
}
