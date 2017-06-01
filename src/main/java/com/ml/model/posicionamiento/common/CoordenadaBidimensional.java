package com.ml.model.posicionamiento.common;

import com.ml.model.Planeta;

public interface CoordenadaBidimensional {

    Double getX();

    void setX(final Double x);

    Double getY();

    void setY(final Double y);

    double distancia(final Planeta planeta);
}
