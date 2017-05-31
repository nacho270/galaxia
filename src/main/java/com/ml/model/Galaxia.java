package com.ml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ml.model.posicionamiento.common.CalculadorPosicion;
import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

public class Galaxia {

    private final List<Planeta> planetas;

    private final CalculadorPosicion<?> calculadorPosicion;
    private final Map<EventoGalaxia, Integer> mapEventoCantidad = new HashMap<>();

    private int diaActual = 0;

    public Galaxia(final EstrategiaPosicionamiento<?, CalculadorPosicion<?>> estrategiaPosicionamiento) {
        calculadorPosicion = estrategiaPosicionamiento.getCalculadorPosicion();
        planetas = new ArrayList<>();
        initPlanetas();
    }

    /**
     *
     */
    private void initPlanetas() {
        planetas.add(new Planeta("SOL", (short) 0, 0, true, calculadorPosicion.crearCoordenada(0, 0)));
        planetas.add(new Planeta("Ferengi", (short) 1, 500, true, calculadorPosicion.crearCoordenada(0, 500)));
        planetas.add(new Planeta("Betasoide", (short) 3, 2000, true, calculadorPosicion.crearCoordenada(0, 2000)));
        planetas.add(new Planeta("Vulcano", (short) 5, 1000, false, calculadorPosicion.crearCoordenada(0, 1000)));
    }

    public void incrementarDia() {
        diaActual++;
        for (final Planeta p : planetas) {
            p.setPosicion(calculadorPosicion.calcularPosicion(diaActual, p.getVelocidadAngular(), p.getDistanciaAlSol()));
        }
        calcularEventos();
    }

    private void calcularEventos() {

    }

    /**
     * @return the planetas
     */
    public final List<Planeta> getPlanetas() {
        return planetas;
    }

    /**
     * @return the diaActual
     */
    public final int getDiaActual() {
        return diaActual;
    }
}
