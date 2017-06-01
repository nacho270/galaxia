package com.ml.model.posicionamiento.cartesiano;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ml.model.Planeta;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

public class CalculadorPosicionCartesianoTest {

    private final CalculadorPosicion<CoordenadaCartesiana> calculador = new CalculadorPosicionCartesiana();

    @Test
    public void testCentroCoordenadas() {
        final CoordenadaCartesiana posicion = calculador.calcularPosicion(9, (short) 0, 0);
        assertEquals(0, posicion.getX(), 0d);
        assertEquals(0, posicion.getY(), 0d);
    }

    @Test
    public void testAntihorario() {
        final CoordenadaCartesiana posicion = calculador.calcularPosicion(9, (short) -10, 1000);
        assertEquals((int) (1000 * Math.cos(Math.toRadians(270d))), posicion.getX(), 0d);
        assertEquals((int) (1000 * Math.sin(Math.toRadians(270d))), posicion.getY(), 0d);
    }

    @Test
    public void testHorario() {
        final CoordenadaCartesiana posicion = calculador.calcularPosicion(9, (short) 10, 1000);
        assertEquals((int) (1000 * Math.cos(Math.toRadians(90d))), posicion.getX(), 0d);
        assertEquals((int) (1000 * Math.sin(Math.toRadians(90d))), posicion.getY(), 0d);
    }

    @Test
    public void testSolIncluido() {
        final List<Planeta> planetas = new ArrayList<>();
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaCartesiana(1d, 1d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaCartesiana(1d, -1d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaCartesiana(-2d, 0d));
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);

        assertTrue(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaCartesiana(0d, 0d)));

        p1.setPosicion(new CoordenadaCartesiana(1d, 2d));
        p2.setPosicion(new CoordenadaCartesiana(1d, -1d));
        p3.setPosicion(new CoordenadaCartesiana(-2d, -1d));

        assertTrue(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaCartesiana(0d, 0d)));
    }

    @Test
    public void testSolEnLineaDelTriangulo() {
        final List<Planeta> planetas = new ArrayList<>();
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaCartesiana(0d, 2d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaCartesiana(0d, -2d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaCartesiana(3d, 0d));
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);

        assertTrue(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaCartesiana(0d, 0d)));
    }

    @Test
    public void testSolNoIncluido() {
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaCartesiana(1d, 1d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaCartesiana(2d, 3d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaCartesiana(3d, 1d));
        final List<Planeta> planetas = new ArrayList<>();
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);

        assertFalse(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaCartesiana(0d, 0d)));

        p1.setPosicion(new CoordenadaCartesiana(-2d, 3d));
        p2.setPosicion(new CoordenadaCartesiana(3d, 3d));
        p3.setPosicion(new CoordenadaCartesiana(2d, -2d));

        assertFalse(calculador.distribucionPlanetasContieneSol(planetas, new CoordenadaCartesiana(0d, 0d)));
    }

    @Test
    public void testPlanetasAlineados() {
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaCartesiana(-1d, -1d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaCartesiana(1d, 1d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaCartesiana(2d, 2d));
        final List<Planeta> planetas = new ArrayList<>();
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);
        assertTrue(calculador.estanAlineados(planetas));
        assertTrue(calculador.estanAlineadosConElSol(planetas, new CoordenadaCartesiana(0d, 0d)));
    }

    @Test
    public void testPlanetasNoAlineados() {
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaCartesiana(0d, 2d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaCartesiana(3d, 0d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaCartesiana(0d, -2d));
        final List<Planeta> planetas = new ArrayList<>();
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);
        assertFalse(calculador.estanAlineados(planetas));
        assertFalse(calculador.estanAlineadosConElSol(planetas, new CoordenadaCartesiana(0d, 0d)));
    }
}
