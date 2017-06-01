package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Modela un chain of responsibility para los {@link HandlerEventoGalaxia}.
 */
public class EventosHandlerChain {

    private final HandlerEventoGalaxia primerHandler;

    /**
     * Constructor. Crea el chain con el siguiente orden: <br>
     * 1- {@link HandlerPeriodoSequia} <br>
     * 2- {@link HandlerPeriodoLluvia} <br>
     * 3- {@link HandlerCOPT} <br>
     * 4- {@link HandlerDiaNormal}
     */
    public EventosHandlerChain() {
        primerHandler = new HandlerPeriodoSequia();
        primerHandler.setProximoHandler(
                new HandlerPeriodoLluvia().setProximoHandler(new HandlerCOPT().setProximoHandler(new HandlerDiaNormal())));
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
