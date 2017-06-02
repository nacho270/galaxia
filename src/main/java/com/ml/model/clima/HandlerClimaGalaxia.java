package com.ml.model.clima;

import com.ml.model.Galaxia;
import com.ml.model.SimuladorClima;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Handler abstracto para analizar el clima de la galaxia.
 */
public abstract class HandlerClimaGalaxia {

    private HandlerClimaGalaxia proximoHandler;

    /**
     * @return the proximoHandler
     */
    public final HandlerClimaGalaxia getProximoHandler() {
        return proximoHandler;
    }

    /**
     * @param proximoHandler
     *            the proximoHandler to set
     * @return {@link HandlerClimaGalaxia} Esta instancia.
     */
    public final HandlerClimaGalaxia setProximoHandler(final HandlerClimaGalaxia proximoHandler) {
        this.proximoHandler = proximoHandler;
        return this;
    }

    /**
     * Lanza la cadena de handlers. Primero chequea si el handler aplica y luego
     * lo ejecuta. Sino, ejecuta el proximo handler de la cadena.
     *
     * @param simulador
     *            {@link SimuladorClima} El simulador de clima que se esta
     *            corriendo.
     * @return {@link ClimaGalaxia} El clima determinado por la cadena.
     */
    public ClimaGalaxia ejecutar(final SimuladorClima simulador) {
        if (aplica(simulador.getGalaxia(), simulador.getCalculadorPosicion())) {
            return computar(simulador);
        } else {
            return proximoHandler.ejecutar(simulador);
        }
    }

    /**
     * Determina si un handler aplica para ser ejecutado.
     *
     * @param galaxia
     *            {@link Galaxia} La galaxia a analizar.
     * @param calculadorPosicion
     *            {@link CalculadorPosicion} El calculador de posiciones para
     *            realizar calculos.
     * @return {@link Boolean} Si el handler aplica.
     */
    protected abstract boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion);

    /**
     * Realiza acciones si el handler aplica.
     *
     * @param simulador
     *            {@link SimuladorClima} El simulador de clima que se esta
     *            corriendo.
     * @return {@link ClimaGalaxia} El clima determinado por la cadena.
     */
    protected abstract ClimaGalaxia computar(final SimuladorClima simulador);
}
