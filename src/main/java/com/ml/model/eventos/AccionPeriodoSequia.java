package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

// 1) Alineados incluido sol
public class AccionPeriodoSequia extends AccionEventoGalaxia {

    // determinar si 3 puntos son una recta => Comparacion de pendientes
    // (x2 - x1) / (x3 - x2) = (y2 - y1) / (y3 - y2)

    @Override
    protected boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return calculadorPosicion.estanAlineadosConElSol(galaxia.getPlanetas(), galaxia.getCoordenadasSol());
    }

    @Override
    protected void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(TipoEventoGalaxia.PERIODO_SEQUIA);
    }
}
