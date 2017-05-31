package com.ml.model.posicionamiento.common;

public interface EstrategiaPosicionamiento<T extends CoordenadaBidimensional, C extends CalculadorPosicion<T>> {

    CalculadorPosicion<T> getCalculadorPosicion();
}
