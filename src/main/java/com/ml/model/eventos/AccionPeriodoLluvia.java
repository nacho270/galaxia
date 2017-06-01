package com.ml.model.eventos;

import java.util.List;

import com.ml.model.EventoGalaxia;
import com.ml.model.Galaxia;
import com.ml.model.Planeta;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

//2) Forman triangulo incluido sol
/**
 * No estan alineados, por ende se forma otra figura geometrica. La estrategia
 * es tomar de a 2 puntos (planetas) de la galaxia + las coordeandas del sol y
 * aplicar el algoritmo PNPOLY de Randolph Franklin para que cualquier forma
 * geometrica sea soportada. Si el sol se encuentra en la misma linea del
 * triangulo, se considera incluido.
 *
 * Si se quisiera restringir a triangulos, se toma de a 2 puntos (planetas) de
 * la galaxia + las coordeandas del sol formando un triangulo y se calcula su
 * area. Si la suma de las areas de dichos triangulos es igual a la suma del
 * area del triangulo que forman los puntos (planetas), el sol esta contenido.
 */
public class AccionPeriodoLluvia implements AccionEventoGalaxia {

    @Override
    public boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        if (!calculadorPosicion.estanAlineados(galaxia.getPlanetas())) {
            return calculadorPosicion.distribucionPlanetasContieneSol(galaxia.getPlanetas(), galaxia.getCoordenadasSol());
        }
        return false;
    }

    @Override
    public void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(EventoGalaxia.PERIODO_LLUVIA);
        galaxia.computarPerimetro(perimetro(galaxia));
    }

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
