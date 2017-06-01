package com.ml.model.posicionamiento.common;

import com.ml.model.Planeta;

public interface CoordenadaBidimensional {

    double getX();

    double getY();

    double distancia(final Planeta planeta);
}
