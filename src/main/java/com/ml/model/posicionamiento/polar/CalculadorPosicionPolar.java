package com.ml.model.posicionamiento.polar;

import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class CalculadorPosicionPolar implements CalculadorPosicion<CoordenadaPolar> {

    @Override
    public CoordenadaPolar calcularPosicion(final int dia, final short velocidadAngular, final int distanciaAlSol) {
        final int distanciaRecorrida = (dia * velocidadAngular);
        // final int vueltas = distanciaRecorrida / 360;
        final int angulo = distanciaRecorrida % 360;
        return new CoordenadaPolar(distanciaAlSol, angulo);
    }

    @Override
    public CoordenadaPolar crearCoordenada(final int x, final int y) {
        return new CoordenadaPolar(x, y);
    }
}
