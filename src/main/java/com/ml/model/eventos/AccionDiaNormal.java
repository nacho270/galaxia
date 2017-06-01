package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class AccionDiaNormal extends AccionEventoGalaxia {

    @Override
    protected boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return true;
    }

    @Override
    protected void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(TipoEventoGalaxia.NORMAL);
    }
}
