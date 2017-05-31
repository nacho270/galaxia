package com.ml.model.posicionamiento.polar;

import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class CalculadorPosicionPolar implements CalculadorPosicion<CoordenadaPolar> {

    @Override
    public CoordenadaPolar calcularPosicion(final int dia, final short velocidadAngular, final int distanciaAlSol) {
        int distanciaRecorrida = (dia * velocidadAngular);
        if (distanciaRecorrida < 0) {
            distanciaRecorrida = 360 - Math.abs(distanciaRecorrida % 360);
        }
        // final int vueltas = distanciaRecorrida / 360;
        final int angulo = distanciaRecorrida % 360;
        return new CoordenadaPolar(angulo, distanciaAlSol);
    }

    @Override
    public CoordenadaPolar crearCoordenada(final int x, final int y) {
        return new CoordenadaPolar(x, y);
    }

    // distancia polar
    // sqrt(r1^2 + r2^2 - 2 r1 r2 cos(angulo1 - angulo2))
}
