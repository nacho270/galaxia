package com.ml.model.posicionamiento.common;

public interface CalculadorPosicion<T extends CoordenadaBidimensional> {

    T calcularPosicion(final int dia, final short velocidadAngular, final int distanciaAlSol);

    T crearCoordenada(int x, int y);
}
