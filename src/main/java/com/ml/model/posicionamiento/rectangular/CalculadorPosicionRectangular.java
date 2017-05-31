package com.ml.model.posicionamiento.rectangular;

import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class CalculadorPosicionRectangular implements CalculadorPosicion<CoordenadaRectangular> {

    @Override
    public CoordenadaRectangular calcularPosicion(final int dia, final short velocidadAngular, final int distanciaAlSol) {
        int distanciaRecorrida = (dia * velocidadAngular);
        if (distanciaRecorrida < 0) {
            distanciaRecorrida = 360 - Math.abs(distanciaRecorrida % 360);
        }
        // final int vueltas = distanciaRecorrida / 360;
        final int angulo = distanciaRecorrida % 360;
        return new CoordenadaRectangular(distanciaAlSol * Math.cos(angulo), distanciaAlSol * Math.sin(angulo));
    }

    @Override
    public CoordenadaRectangular crearCoordenada(final int x, final int y) {
        return new CoordenadaRectangular(x, y);
    }
}
