package com.ml.model.posicionamiento.rectangular;

import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class CalculadorPosicionRectangular implements CalculadorPosicion<CoordenadaRectangular> {

    @Override
    public CoordenadaRectangular calcularPosicion(final int dia, final short velocidadAngular, final int distanciaAlSol) {
        final int distanciaRecorrida = (dia * velocidadAngular);
        // final int vueltas = distanciaRecorrida / 360;
        final int angulo = distanciaRecorrida % 360;
        return new CoordenadaRectangular(distanciaAlSol * Math.cos(angulo), distanciaAlSol * Math.sin(angulo));
    }

    @Override
    public CoordenadaRectangular crearCoordenada(final int x, final int y) {
        return new CoordenadaRectangular(x, y);
    }

}
