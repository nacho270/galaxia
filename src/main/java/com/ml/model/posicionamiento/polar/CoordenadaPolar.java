package com.ml.model.posicionamiento.polar;

import com.ml.model.Planeta;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

public class CoordenadaPolar implements CoordenadaBidimensional {

    private final double radio;
    private final double angulo;

    public CoordenadaPolar(final double angulo, final double radio) {
        this.angulo = angulo;
        this.radio = radio;
    }

    @Override
    public final double getX() {
        return radio;
    }

    @Override
    public final double getY() {
        return angulo;
    }

    @Override
    public double distancia(Planeta planeta) {
        // sqrt(r1^2 + r2^2 - 2 r1 r2 cos(angulo1 - angulo2))
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(planeta.getPosicion().getX(), 2)
                        - 2 * getX() * planeta.getPosicion().getX() * Math.cos(getY() - planeta.getPosicion().getY()));
    }
}
