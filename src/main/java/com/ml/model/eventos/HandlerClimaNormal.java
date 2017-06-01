package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Handler default para cuando no ocurre ningun evento. Debe estar siempre al final de la cadena.
 */
public class HandlerClimaNormal extends HandlerClimaGalaxia {

    @Override
    protected boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return true;
    }

    @Override
    protected void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(TipoClimaGalaxia.NORMAL);
    }
}
