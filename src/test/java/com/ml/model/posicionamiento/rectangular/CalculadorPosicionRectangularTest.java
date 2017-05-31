package com.ml.model.posicionamiento.rectangular;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class CalculadorPosicionRectangularTest {

    private final CalculadorPosicion<CoordenadaRectangular> calculador = new CalculadorPosicionRectangular();

    @Test
    public void testCentroCoordenadas() {
        final CoordenadaRectangular posicion = calculador.calcularPosicion(9, (short) 0, 0);
        assertEquals(0, posicion.getX(), 0d);
        assertEquals(0, posicion.getY(), 0d);
    }

    @Test
    public void testAntihorario() {
        final CoordenadaRectangular posicion = calculador.calcularPosicion(9, (short) -10, 1000);
        assertEquals(1000 * Math.cos(270d), posicion.getX(), 0d);
        assertEquals(1000 * Math.sin(270d), posicion.getY(), 0d);
    }

    @Test
    public void testHorario() {
        final CoordenadaRectangular posicion = calculador.calcularPosicion(9, (short) 10, 1000);
        assertEquals(1000 * Math.cos(90d), posicion.getX(), 0d);
        assertEquals(1000 * Math.sin(90d), posicion.getY(), 0d);
    }
}
