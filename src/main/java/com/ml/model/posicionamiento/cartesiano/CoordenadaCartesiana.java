package com.ml.model.posicionamiento.cartesiano;

import com.ml.model.Planeta;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

public class CoordenadaCartesiana implements CoordenadaBidimensional {

    private final double x;
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
    public double distancia(Planeta planeta) {
        return Math.sqrt(Math.pow(planeta.getPosicion().getX() - this.getX(), 2)
                        + Math.pow(planeta.getPosicion().getY() - this.getY(), 2));
    }

    @Override
    public String toString() {
        return "(x = " + x + ", y = " + y + ")";
    }
}
