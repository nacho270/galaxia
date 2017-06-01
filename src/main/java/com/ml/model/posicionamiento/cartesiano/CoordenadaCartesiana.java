package com.ml.model.posicionamiento.cartesiano;

import javax.persistence.Embeddable;

import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

/**
 * Implementacion cartediana de una {@link CoordenadaBidimensional}.
 */
@Embeddable
public class CoordenadaCartesiana extends CoordenadaBidimensional {

    /**
     * Constructor.
     */
    public CoordenadaCartesiana() {
        super();
    }

    /**
     * Constructor.
     *
     * @param x
     *            {@link Double} Coordenada X.
     * @param y
     *            {@link Double} Coordenada Y.
     */
    public CoordenadaCartesiana(final double x, final double y) {
        super(x, y);
    }

    /**
     * Calcula la distancia de esta coordenada hacia otra mediante el teoroma de pitagoras.
     *
     * @param coordenadaBidimensional
     *            {@link CoordenadaBidimensional} La coordenada hacia donde se quiere calcular la distancia.
     * @return {@link Double} La distancia entre este planeta y el solicitado.
     */
    @Override
    public double distancia(final CoordenadaBidimensional coordenadaBidimensional) {
        return Math
                .sqrt(Math.pow(coordenadaBidimensional.getX() - getX(), 2) + Math.pow(coordenadaBidimensional.getY() - getY(), 2));
    }

    @Override
    public String toString() {
        return "(x = " + getX() + ", y = " + getY() + ")";
    }
}
