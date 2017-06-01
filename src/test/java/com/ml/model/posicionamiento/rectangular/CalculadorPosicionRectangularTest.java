package com.ml.model.posicionamiento.rectangular;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ml.model.Planeta;
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

    @Test
    public void testSolIncluido() {
        final List<Planeta> planetas = new ArrayList<>();
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaRectangular(1d, 1d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaRectangular(1d, -1d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaRectangular(-2d, 0d));
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);

        assertTrue(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaRectangular(0d, 0d)));

        p1.setPosicion(new CoordenadaRectangular(1d, 2d));
        p2.setPosicion(new CoordenadaRectangular(1d, -1d));
        p3.setPosicion(new CoordenadaRectangular(-2d, -1d));

        assertTrue(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaRectangular(0d, 0d)));
    }

    @Test
    public void testSolEnLineaDelTriangulo() {
        final List<Planeta> planetas = new ArrayList<>();
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaRectangular(0d, 2d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaRectangular(0d, -2d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaRectangular(3d, 0d));
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);

        assertTrue(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaRectangular(0d, 0d)));
    }

    @Test
    public void testSolNoIncluido() {
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaRectangular(1d, 1d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaRectangular(2d, 3d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaRectangular(3d, 1d));
        final List<Planeta> planetas = new ArrayList<>();
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);

        assertFalse(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaRectangular(0d, 0d)));

        p1.setPosicion(new CoordenadaRectangular(-2d, 3d));
        p2.setPosicion(new CoordenadaRectangular(3d, 3d));
        p3.setPosicion(new CoordenadaRectangular(2d, -2d));

        assertFalse(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaRectangular(0d, 0d)));
    }

    @Test
    public void testPlanetasAlineados() {
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaRectangular(-1d, -1d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaRectangular(1d, 1d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaRectangular(2d, 2d));
        final List<Planeta> planetas = new ArrayList<>();
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);
        assertTrue(calculador.estanAlineados(planetas));
        assertTrue(calculador.estanAlineadosConElSol(planetas, new CoordenadaRectangular(0d, 0d)));
    }

    @Test
    public void testPlanetasNoAlineados() {
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaRectangular(0d, 2d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaRectangular(3d, 0d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaRectangular(0d, -2d));
        final List<Planeta> planetas = new ArrayList<>();
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);
        assertFalse(calculador.estanAlineados(planetas));
        assertFalse(calculador.estanAlineadosConElSol(planetas, new CoordenadaRectangular(0d, 0d)));
    }
}
