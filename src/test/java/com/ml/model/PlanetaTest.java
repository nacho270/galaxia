package com.ml.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ml.model.posicionamiento.cartesiano.CoordenadaCartesiana;

public class PlanetaTest {

    @Test
    public void testConstruccionHoraria() {
        final Planeta p = new Planeta("test", (short) 10, 10, true, new CoordenadaCartesiana(0d, 0d));
        assertTrue(p.getVelocidadAngular() < 0);
    }

    @Test
    public void testConstruccionAntiHoraria() {
        final Planeta p = new Planeta("test", (short) 10, 10, false, new CoordenadaCartesiana(0d, 0d));
        assertTrue(p.getVelocidadAngular() > 0);
    }
}
