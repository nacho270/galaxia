package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Handler abstracto para eventos de la galaxia.
 */
public abstract class HandlerEventoGalaxia {

    private HandlerEventoGalaxia proximoHandler;

    /**
     * @return the proximoHandler
     */
    public final HandlerEventoGalaxia getProximoHandler() {
        return proximoHandler;
    }

    /**
     * @param proximoHandler
     *            the proximoHandler to set
     * @return {@link HandlerEventoGalaxia} Esta instancia.
     */
    public final HandlerEventoGalaxia setProximoHandler(final HandlerEventoGalaxia proximoHandler) {
        this.proximoHandler = proximoHandler;
        return this;
    }

    /**
     * Lanza la cadena de handlers. Primero chequea si el handler aplica y luego lo ejecuta. Sino, ejecuta el proximo
     * handler de la cadena.
     *
     * @param galaxia
     *            {@link Galaxia} La galaxia a analizar.
     * @param calculadorPosicion
     *            {@link CalculadorPosicion} El calculador de posiciones para realizar calculos.
     */
    public void ejecutar(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        if (aplica(galaxia, calculadorPosicion)) {
            computar(galaxia);
        } else {
            proximoHandler.ejecutar(galaxia, calculadorPosicion);
        }
    }

    /**
     * Determina si un handler aplica para ser ejecutado.
     *
     * @param galaxia
     *            {@link Galaxia} La galaxia a analizar.
     * @param calculadorPosicion
     *            {@link CalculadorPosicion} El calculador de posiciones para realizar calculos.
     * @return {@link Boolean} Si el handler aplica.
     */
    protected abstract boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion);

    /**
     * Realiza acciones si el handler aplica.
     *
     * @param galaxia
     *            {@link Galaxia} La galaxia a analizar.
     */
    protected abstract void computar(final Galaxia galaxia);
}
