package com.ml.model.clima;

import java.util.List;

import com.ml.model.Galaxia;
import com.ml.model.Planeta;
import com.ml.model.SimuladorClima;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Handler para el clima de "Periodo de lluvia".<br>
 * Se ejecuta solo si los planetas no se encuentran alineados entre si y el sol
 * se encuentra dentro del poligono formado por los mismos. <br>
 * Al ejecutar, computa el perimetro del poligono actual que forma los planetas
 * y se lo notifica a la galaxia.
 */
public class HandlerClimaLluvia extends HandlerClimaGalaxia {

    @Override
    protected boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        if (!calculadorPosicion.estanAlineados(galaxia.getPlanetas())) {
            // No estan alineados, por ende se forma otra figura geometrica.
            return calculadorPosicion.distribucionPlanetasContieneSol(galaxia.getPlanetas(), galaxia.getCoordenadasSol());
        }
        return false;
    }

    @Override
    protected ClimaGalaxia computar(final SimuladorClima simulador) {
        simulador.computarPerimetro(perimetro(simulador.getGalaxia()));
        return simulador.sumarDiaConClima(TipoClimaGalaxia.LLUVIA);
    }

    /**
     * Calcula el perimetro del poligono formado por los planetas en la galaxia.
     *
     * @param galaxia
     *            {@link Galaxia} La galaxia para calcular el perimetro.
     * @return {@link Double} El perimetro.
     */
    protected double perimetro(final Galaxia galaxia) {
        int i = 0;
        double perimetro = 0d;
        final List<Planeta> planetas = galaxia.getPlanetas();
        while (i < planetas.size() - 1) {
            perimetro += planetas.get(i).distancia(planetas.get(i + 1));
            i++;
        }
        perimetro += planetas.get(0).distancia(planetas.get(planetas.size() - 1));
        return perimetro;
    }
}
