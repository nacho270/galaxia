package com.ml.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ml.model.posicionamiento.rectangular.CoordenadaRectangular;

public class PlanetaTest {

    @Test
    public void testConstruccionHoraria() {
        final Planeta p = new Planeta("test", (short) 10, 10, true, new CoordenadaRectangular(0d, 0d));
        assertTrue(p.getVelocidadAngular() < 0);
    }

    @Test
    public void testConstruccionAntiHoraria() {
        final Planeta p = new Planeta("test", (short) 10, 10, false, new CoordenadaRectangular(0d, 0d));
        assertTrue(p.getVelocidadAngular() > 0);
    }
}
