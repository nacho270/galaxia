package com.ml.model;

import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

public class Sol extends Planeta {

    public Sol(final CoordenadaBidimensional posicionInicial) {
        super("SOL", (short) 0, 0, true, posicionInicial);
    }

}
