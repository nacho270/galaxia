package com.ml.model.posicionamiento.cartesiano;

import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

/**
 * Implementacion cartesiana de una {@link EstrategiaPosicionamiento} mediante: {@link CoordenadaCartesiana} y
 * {@link CalculadorPosicionCartesiana}.
 */
public class EstrategiaCartesiana implements EstrategiaPosicionamiento<CoordenadaCartesiana, CalculadorPosicionCartesiana> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculadorPosicionCartesiana getCalculadorPosicion() {
        return new CalculadorPosicionCartesiana();
    }
}
