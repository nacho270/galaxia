package com.ml.model.clima;

import com.ml.model.Galaxia;
import com.ml.model.SimuladorClima;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Handler default para cuando no ocurre ningun tipo de clima en particular.
 * Debe estar siempre al final de la cadena.
 */
public class HandlerClimaNormal extends HandlerClimaGalaxia {

    @Override
    protected boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return true;
    }

    @Override
    protected ClimaGalaxia computar(final SimuladorClima simulador) {
        return simulador.sumarDiaConClima(TipoClimaGalaxia.NORMAL);
    }
}
