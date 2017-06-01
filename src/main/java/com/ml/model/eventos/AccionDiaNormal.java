package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class AccionDiaNormal implements AccionEventoGalaxia {

    @Override
    public boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return true;
    }

    @Override
    public void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(TipoEventoGalaxia.NORMAL);
    }
}
