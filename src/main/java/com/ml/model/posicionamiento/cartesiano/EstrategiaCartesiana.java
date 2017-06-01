package com.ml.model.posicionamiento.cartesiano;

import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

public class EstrategiaCartesiana implements EstrategiaPosicionamiento<CoordenadaCartesiana, CalculadorPosicionCartesiana> {

    @Override
    public CalculadorPosicionCartesiana getCalculadorPosicion() {
        return new CalculadorPosicionCartesiana();
    }

}
