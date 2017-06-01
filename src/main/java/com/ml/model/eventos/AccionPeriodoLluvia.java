package com.ml.model.eventos;

import java.util.List;

import com.ml.model.EventoGalaxia;
import com.ml.model.Galaxia;
import com.ml.model.Planeta;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

//2) Forman triangulo incluido sol
public class AccionPeriodoLluvia implements AccionEventoGalaxia {

    @Override
    public boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        if (!calculadorPosicion.estanAlineados(galaxia.getPlanetas())) {
            // No estan alineados, por ende se forma otra figura geometrica.
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
