package com.ml.model.posicionamiento.cartesiano;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.ml.model.Planeta;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

@Embeddable
public class CoordenadaCartesiana implements CoordenadaBidimensional {

    private Double x;
    private Double y;

    public CoordenadaCartesiana() {

    }

    public CoordenadaCartesiana(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    @Column(name = "X", nullable = false)
    public Double getX() {
        return x;
    }

    @Override
    @Column(name = "Y", nullable = false)
    public Double getY() {
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

    @Override
    public void setX(final Double x) {
        this.x = x;
    }

    @Override
    public void setY(final Double y) {
        this.y = y;
    }
}
