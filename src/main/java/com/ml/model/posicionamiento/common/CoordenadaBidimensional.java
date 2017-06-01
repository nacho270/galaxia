package com.ml.model.posicionamiento.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Representa un punto en un plano con 2 coordenadas.
 */
@MappedSuperclass
public abstract class CoordenadaBidimensional {

    private Double x;
    private Double y;

    /**
     * Constructor.
     */
    public CoordenadaBidimensional() {

    }

    /**
     * Constructor.
     *
     * @param x
     *            {@link Double} Coordenada X.
     * @param y
     *            {@link Double} Coordenada Y.
     */
    public CoordenadaBidimensional(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    @Column(name = "X", nullable = false)
    public final Double getX() {
        return x;
    }

    /**
     * @param x
     *            the x to set
     */
    public final void setX(final Double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    @Column(name = "Y", nullable = false)
    public final Double getY() {
        return y;
    }

    /**
     * @param y
     *            the y to set
     */
    public final void setY(final Double y) {
        this.y = y;
    }

    /**
     * Calcula la distancia de esta coordenada hacia otra.
     *
     * @param coordenadaBidimensional
     *            {@link CoordenadaBidimensional} La coordenada para la se quiere calcular la distancia.
     * @return {@link Double} La distancia entre esta coordenada y la solicitada.
     */
    public abstract double distancia(final CoordenadaBidimensional coordenadaBidimensional);
}
