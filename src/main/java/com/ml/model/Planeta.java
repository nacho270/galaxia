package com.ml.model;

import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

public class Planeta {

    private final String nombre;
    private final short velocidadAngular;
    private final int distanciaAlSol;

    private CoordenadaBidimensional posicion;

    public Planeta(final String nombre, final short velocidadAngular, final int distanciaAlSol, final boolean horaria,
            final CoordenadaBidimensional posicionInicial) {
        this.nombre = nombre;
        this.distanciaAlSol = distanciaAlSol;
        this.velocidadAngular = (short) ((horaria ? 1 : -1) * velocidadAngular);
        posicion = posicionInicial;
    }

    /**
     * @return the nombre
     */
    public final String getNombre() {
        return nombre;
    }

    /**
     * @return the velocidadAngular
     */
    public final short getVelocidadAngular() {
        return velocidadAngular;
    }

    /**
     * @return the posicion
     */
    public final CoordenadaBidimensional getPosicion() {
        return posicion;
    }

    /**
     * @param posicion
     *            the posicion to set
     */
    public final void setPosicion(final CoordenadaBidimensional posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the distanciaAlSol
     */
    public final int getDistanciaAlSol() {
        return distanciaAlSol;
    }
}
