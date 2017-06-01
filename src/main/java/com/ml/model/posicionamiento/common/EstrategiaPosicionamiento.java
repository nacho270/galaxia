package com.ml.model.posicionamiento.common;

/**
 * Estrategia generica de posicionamiento. Util para cambiar el sistema de coordenadas del sistema.
 *
 * @param <T>
 *            El tipo de coordenada bidimensional.
 * @param <C>
 *            El tipo de calculador de posicion.
 */
public interface EstrategiaPosicionamiento<T extends CoordenadaBidimensional, C extends CalculadorPosicion<T>> {

    /**
     * Obtiene la implementacion de un {@link CalculadorPosicion} a utilizar.
     *
     * @return {@link CalculadorPosicion}.
     */
    C getCalculadorPosicion();
}
