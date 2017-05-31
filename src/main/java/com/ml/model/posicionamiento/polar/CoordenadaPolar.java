package com.ml.model.posicionamiento.polar;

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
}
