package com.ml.model.posicionamiento.polar;

import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

public class EstrategiaPolar implements EstrategiaPosicionamiento<CoordenadaPolar, CalculadorPosicionPolar> {

    @Override
    public CalculadorPosicionPolar getCalculadorPosicion() {
        return new CalculadorPosicionPolar();
    }
}
