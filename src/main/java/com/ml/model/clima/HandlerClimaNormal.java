package com.ml.model.clima;

import com.ml.model.Galaxia;
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
    protected void computar(final Galaxia galaxia) {
        galaxia.sumarDiaConClima(TipoClimaGalaxia.NORMAL);
    }
}
