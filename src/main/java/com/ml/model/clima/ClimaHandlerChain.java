package com.ml.model.clima;

import com.ml.model.SimuladorClima;

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
     * @param simulador
     *            {@link SimuladorClima} El simulador que se esta corriendo.
     * @return {@link ClimaGalaxia} El clima generado por la nueva posicion.
     */
    public ClimaGalaxia ejecutar(final SimuladorClima simulador) {
        return primerHandler.ejecutar(simulador);
    }
}
