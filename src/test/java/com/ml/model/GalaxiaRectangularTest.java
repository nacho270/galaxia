package com.ml.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;

public class GalaxiaRectangularTest {

    private final Galaxia galaxiaRecatangular = new Galaxia(new EstrategiaCartesiana());

    @Before
    public void init() {
        galaxiaRecatangular.getPlanetas().clear();
        galaxiaRecatangular.addPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxiaRecatangular.addPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxiaRecatangular.addPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);
    }

    @Test
    public void testUnDia() {
        galaxiaRecatangular.simularHasta(1);
        assertEquals(1, galaxiaRecatangular.getDiaActual());

        final Planeta ferengi = galaxiaRecatangular.getPlaneta("Ferengi");
        final Planeta betasoide = galaxiaRecatangular.getPlaneta("Betasoide");
        final Planeta vulcano = galaxiaRecatangular.getPlaneta("Vulcano");

        assertCoordenadas(359, ferengi);
        assertCoordenadas(357, betasoide);
        assertCoordenadas(5, vulcano);
    }

    @Test
    public void testPlanetaRecorridoHorarioMasDeUnaVuelta() {
        galaxiaRecatangular.simularHasta(400);
        assertEquals(400, galaxiaRecatangular.getDiaActual());
        final Planeta ferengi = galaxiaRecatangular.getPlaneta("Ferengi");
        assertCoordenadas(320, ferengi);
    }

    private static void assertCoordenadas(final int angulo, final Planeta planeta) {
        assertEquals((int) (Math.cos(Math.toRadians(angulo)) * planeta.getDistanciaAlSol()), planeta.getPosicion().getX(), 0d);
        assertEquals((int) (Math.sin(Math.toRadians(angulo)) * planeta.getDistanciaAlSol()), planeta.getPosicion().getY(), 0d);
    }

    @Test
    public void testComputarPerimetro() {
        galaxiaRecatangular.computarPerimetro(10d);
        galaxiaRecatangular.computarPerimetro(1d);
        galaxiaRecatangular.computarPerimetro(20d);
        assertEquals(20d, galaxiaRecatangular.getPerimetroMaximo(), 0d);
    }
}
