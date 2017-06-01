package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

public abstract class AccionEventoGalaxia {

    private AccionEventoGalaxia proximoHandler;

    public void ejecutar(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        if (aplica(galaxia, calculadorPosicion)) {
            computar(galaxia);
        } else {
            proximoHandler.ejecutar(galaxia, calculadorPosicion);
        }
    }

    /**
     * @return the proximoHandler
     */
    public final AccionEventoGalaxia getProximoHandler() {
        return proximoHandler;
    }

    /**
     * @param proximoHandler
     *            the proximoHandler to set
     */
    public final AccionEventoGalaxia setProximoHandler(final AccionEventoGalaxia proximoHandler) {
        this.proximoHandler = proximoHandler;
        return this;
    }

    protected abstract boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion);

    protected abstract void computar(final Galaxia galaxia);
}
