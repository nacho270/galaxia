package com.ml.model.posicionamiento.polar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class CalculadorPosicionPolarTest {

    private final CalculadorPosicion<CoordenadaPolar> calculador = new CalculadorPosicionPolar();

    @Test
    public void testCentroCoordenadas() {
        final CoordenadaPolar posicion = calculador.calcularPosicion(9, (short) 0, 0);
        assertEquals(0, posicion.getX(), 0d);
        assertEquals(0, posicion.getY(), 0d);
    }

    @Test
    public void testAntihorario() {
        final CoordenadaPolar posicion = calculador.calcularPosicion(9, (short) -10, 1000);
        assertEquals(1000, posicion.getX(), 0d);
        assertEquals(270, posicion.getY(), 0d);
    }

    @Test
    public void testHorario() {
        final CoordenadaPolar posicion = calculador.calcularPosicion(9, (short) 10, 1000);
        assertEquals(1000, posicion.getX(), 0d);
        assertEquals(90, posicion.getY(), 0d);
    }
}
