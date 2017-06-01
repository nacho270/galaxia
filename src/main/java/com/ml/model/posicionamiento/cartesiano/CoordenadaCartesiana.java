package com.ml.model.posicionamiento.cartesiano;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.ml.model.Planeta;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

@Embeddable
public class CoordenadaCartesiana implements CoordenadaBidimensional {

    @Column(name = "X")
    private final double x;

    @Column(name = "Y")
    private final double y;

    public CoordenadaCartesiana(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double distancia(final Planeta planeta) {
        return Math.sqrt(Math.pow(planeta.getPosicion().getX() - getX(), 2) + Math.pow(planeta.getPosicion().getY() - getY(), 2));
    }

    @Override
    public String toString() {
        return "(x = " + x + ", y = " + y + ")";
    }
}
