package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

public interface AccionEventoGalaxia {

    boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion);

    void computar(final Galaxia galaxia);
}
