package com.ml.model.clima;

import com.ml.model.Galaxia;
import com.ml.model.SimuladorClima;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Handler para el elcima de "Periodo de sequia".<br>
 * Se ejecuta solo si los planetas se encuentran alineados entre si.
 */
public class HandlerClimaSequia extends HandlerClimaGalaxia {

    @Override
    protected boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return calculadorPosicion.estanAlineadosConElSol(galaxia.getPlanetas(), galaxia.getCoordenadasSol());
    }

    @Override
    protected ClimaGalaxia computar(final SimuladorClima simulador) {
        return simulador.sumarDiaConClima(TipoClimaGalaxia.SEQUIA);
    }
}
