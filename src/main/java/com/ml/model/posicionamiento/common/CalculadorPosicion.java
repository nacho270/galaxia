package com.ml.model.posicionamiento.common;

import java.util.List;

import com.ml.model.Planeta;

public interface CalculadorPosicion<T extends CoordenadaBidimensional> {

    T calcularPosicion(final int dia, final short velocidadAngular, final int distanciaAlSol);

    T crearCoordenada(int x, int y);

    boolean estanAlineadosConElSol(final List<Planeta> planetas, final CoordenadaBidimensional coordenadasSol);

    boolean estanAlineados(final List<Planeta> planetas);

    boolean distribucionPlanetasContieneSol(final List<Planeta> planetas, final CoordenadaBidimensional coordenadasSol);

}
