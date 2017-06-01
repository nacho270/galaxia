package com.ml.model.clima;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Modela un chain of responsibility para los {@link HandlerClimaGalaxia}.
 */
public class ClimaHandlerChain {

    private final HandlerClimaGalaxia primerHandler;

    /**
     * Constructor. Crea el chain con el siguiente orden: <br>
     * 1- {@link HandlerClimaSequia} <br>
     * 2- {@link HandlerClimaLluvia} <br>
     * 3- {@link HandlerCOPT} <br>
     * 4- {@link HandlerClimaNormal}
     */
    public ClimaHandlerChain() {
        primerHandler = new HandlerClimaSequia();
        primerHandler.setProximoHandler(
                new HandlerClimaLluvia().setProximoHandler(new HandlerCOPT().setProximoHandler(new HandlerClimaNormal())));
    }

    /**
     * Lanza la cadena de ejecucion de handlers.
     *
     * @param galaxia
     *            {@link Galaxia} La galaxia a analizar.
     * @param calculadorPosicion
     *            {@link CalculadorPosicion} El calculador de posiciones para realizar los calculos.
     */
    public void ejecutar(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        primerHandler.ejecutar(galaxia, calculadorPosicion);
    }
}
