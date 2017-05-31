package com.ml.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.posicionamiento.polar.EstrategiaPolar;

public class GalaxiaPolarTest {

    private final Galaxia galaxiaPolar = new Galaxia(new EstrategiaPolar());

    @Before
    public void init() {
        galaxiaPolar.getPlanetas().clear();
        galaxiaPolar.addPlaneta("SOL", (short) 0, 0, true, 0, 0);
        galaxiaPolar.addPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxiaPolar.addPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxiaPolar.addPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);
    }

    @Test
    public void testUnDia() {
        galaxiaPolar.simularHasta(1);
        assertEquals(1, galaxiaPolar.getDiaActual());

        final Planeta ferengi = galaxiaPolar.getPlaneta("Ferengi");
        final Planeta betasoide = galaxiaPolar.getPlaneta("Betasoide");
        final Planeta vulcano = galaxiaPolar.getPlaneta("Vulcano");

        assertCoordenadas(359, ferengi);
        assertCoordenadas(357, betasoide);
        assertCoordenadas(5, vulcano);
    }

    @Test
    public void testPlanetaRecorridoHorarioMasDeUnaVuelta() {
        galaxiaPolar.simularHasta(400);
        assertEquals(400, galaxiaPolar.getDiaActual());
        final Planeta ferengi = galaxiaPolar.getPlaneta("Ferengi");
        assertCoordenadas(320, ferengi);
    }

    private static void assertCoordenadas(final int angulo, final Planeta planeta) {
        assertEquals(planeta.getDistanciaAlSol(), planeta.getPosicion().getX(), 0d);
        assertEquals(angulo, planeta.getPosicion().getY(), 0d);
    }
}
