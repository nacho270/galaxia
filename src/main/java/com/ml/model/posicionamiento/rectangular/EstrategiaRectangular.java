package com.ml.model.posicionamiento.rectangular;

import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

public class EstrategiaRectangular implements EstrategiaPosicionamiento<CoordenadaRectangular, CalculadorPosicionRectangular> {

    @Override
    public CalculadorPosicionRectangular getCalculadorPosicion() {
        return new CalculadorPosicionRectangular();
    }

}
