package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Handler para el evento de "Periodo de sequia".<br>
 * Se ejecuta solo si los planetas se encuentran alineados entre si.
 */
public class HandlerPeriodoSequia extends HandlerEventoGalaxia {

    @Override
    protected boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return calculadorPosicion.estanAlineadosConElSol(galaxia.getPlanetas(), galaxia.getCoordenadasSol());
    }

    @Override
    protected void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(TipoEventoGalaxia.PERIODO_SEQUIA);
    }
}
