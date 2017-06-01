package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class EventosChain {

    private final AccionEventoGalaxia primerHandler;

    public EventosChain() {
        primerHandler = new AccionPeriodoSequia();
        primerHandler.setProximoHandler(
                new AccionPeriodoLluvia().setProximoHandler(new AccionCOPT().setProximoHandler(new AccionDiaNormal())));
    }

    public void ejecutar(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        primerHandler.ejecutar(galaxia, calculadorPosicion);
    }
}
