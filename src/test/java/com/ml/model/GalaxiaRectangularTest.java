package com.ml.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.posicionamiento.rectangular.EstrategiaRectangular;

public class GalaxiaRectangularTest {

    private final Galaxia galaxiaRecatangular = new Galaxia(new EstrategiaRectangular());

    @Before
    public void init() {
        galaxiaRecatangular.getPlanetas().clear();
        galaxiaRecatangular.addPlaneta("SOL", (short) 0, 0, true, 0, 0);
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
        assertEquals(Math.cos(angulo) * planeta.getDistanciaAlSol(), planeta.getPosicion().getX(), 0d);
        assertEquals(Math.sin(angulo) * planeta.getDistanciaAlSol(), planeta.getPosicion().getY(), 0d);
    }
}
