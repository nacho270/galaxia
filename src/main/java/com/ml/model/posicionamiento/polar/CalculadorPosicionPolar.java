package com.ml.model.posicionamiento.polar;

import java.util.List;

import com.ml.model.Planeta;
import com.ml.model.posicionamiento.common.CalculadorPosicion;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

public class CalculadorPosicionPolar implements CalculadorPosicion<CoordenadaPolar> {

    @Override
    public CoordenadaPolar calcularPosicion(final int dia, final short velocidadAngular, final int distanciaAlSol) {
        int distanciaRecorrida = (dia * velocidadAngular);
        if (distanciaRecorrida < 0) {
            distanciaRecorrida = 360 - Math.abs(distanciaRecorrida % 360);
        }
        // final int vueltas = distanciaRecorrida / 360;
        final int angulo = distanciaRecorrida % 360;
        return new CoordenadaPolar(angulo, distanciaAlSol);
    }

    @Override
    public CoordenadaPolar crearCoordenada(final int x, final int y) {
        return new CoordenadaPolar(x, y);
    }

    @Override
    public boolean estanAlineadosConElSol(final List<Planeta> planetas, final CoordenadaBidimensional coordenadasSol) {
        throw new UnsupportedOperationException("No implementado");
    }

    @Override
    public boolean estanAlineados(final List<Planeta> planetas) {
        throw new UnsupportedOperationException("No implementado");
    }

    @Override
    public boolean distribucionPlanetasContieneSol(List<Planeta> planetas, CoordenadaBidimensional coordenadasSol) {
        throw new UnsupportedOperationException("No implementado");
    }
}
