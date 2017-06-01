package com.ml.model.posicionamiento.common;

import java.util.List;

import com.ml.model.Planeta;

/**
 * Interfar para el calculo posiciones y analisis de las mismas.
 *
 * @param <T>
 *            El tipo de coordenada a utilizar.
 */
public interface CalculadorPosicion<T extends CoordenadaBidimensional> {

    /**
     * Calcula una posicion en funcion del dia, la velocidad angular y la distancia al sol.
     *
     * @param dia
     *            {@link Integer} El tiempo de desplazamiento.
     * @param velocidadAngular
     *            {@link Integer} La velocidad de desplazamiento.
     * @param distanciaAlSol
     *            {@link Integer} La distnacia al sol (radio).
     * @return Una {@link CoordenadaBidimensional}.
     */
    T calcularPosicion(final int dia, final short velocidadAngular, final int distanciaAlSol);

    /**
     * Crea una {@link CoordenadaBidimensional}.
     *
     * @param x
     *            {@link Integer} La coordenada X.
     * @param y
     *            {@link Integer} La coordenada Y.
     * @return Una {@link CoordenadaBidimensional}.
     */
    T crearCoordenada(int x, int y);

    /**
     * Chequea si los planetas estan alineados entre si y con el sol.
     *
     * @param planetas
     *            {@link List} Los planetas.
     * @param coordenadasSol
     *            {@link CoordenadaBidimensional} Las coordenadas del sol.
     * @return {@link Boolean} Si estan alineados entre si y con el sol.
     */
    boolean estanAlineadosConElSol(final List<Planeta> planetas, final CoordenadaBidimensional coordenadasSol);

    /**
     * Chequea si los planetas estan alineados entre si.
     *
     * @param planetas
     *            {@link List} Los planetas.
     * @return {@link Boolean} Si estan alineados entre si.
     */
    boolean estanAlineados(final List<Planeta> planetas);

    /**
     * Chequea si el sol se encuentra del poligono formado por los planetas.
     *
     * @param planetas
     *            {@link List} Los planetas.
     * @param coordenadasSol
     *            {@link CoordenadaBidimensional} Las coordenadas del sol.
     * @return {@link Boolean} Si el sol se encuentra del poligono formado por los planetas
     */
    boolean distribucionPlanetasContieneSol(final List<Planeta> planetas, final CoordenadaBidimensional coordenadasSol);

}
