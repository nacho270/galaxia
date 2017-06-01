package com.ml.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;

public class GalaxiaRectangularTest {

    private final Galaxia galaxia = spy(new Galaxia(new EstrategiaCartesiana()));

    @Before
    public void init() {
        galaxia.getPlanetas().clear();
    }

    @Test
    public void testUnDia() {
        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);

        galaxia.simularHasta(1);
        assertEquals(1, galaxia.getDiaActual());

        final Planeta ferengi = galaxia.getPlaneta("Ferengi");
        final Planeta betasoide = galaxia.getPlaneta("Betasoide");
        final Planeta vulcano = galaxia.getPlaneta("Vulcano");

        assertCoordenadas(359, ferengi);
        assertCoordenadas(357, betasoide);
        assertCoordenadas(5, vulcano);
    }

    @Test
    public void testPlanetaRecorridoHorarioMasDeUnaVuelta() {
        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);

        galaxia.simularHasta(400);
        assertEquals(400, galaxia.getDiaActual());
        final Planeta ferengi = galaxia.getPlaneta("Ferengi");
        assertCoordenadas(320, ferengi);
    }

    @Test
    public void testIncrementarDiaParaGenerarCOPT() {
        doNothing().when(galaxia).actualizarPosiciones();

        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, -500, 350);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, -1000, 350);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, -2000, 350);

        galaxia.simularHasta(1);

        assertEquals(1, galaxia.getMapEventoCantidad().get(EventoGalaxia.COPT), 0d);
    }

    @Test
    public void testIncrementarDiaParaGenerarSequia() {
        doNothing().when(galaxia).actualizarPosiciones();

        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, 1, 1);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, 2, 2);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, 3, 3);

        galaxia.simularHasta(1);

        assertEquals(1, galaxia.getMapEventoCantidad().get(EventoGalaxia.PERIODO_SEQUIA), 0d);
    }

    @Test
    public void testIncrementarDiaParaGenerarLluvia() {
        doNothing().when(galaxia).actualizarPosiciones();

        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, 0, 2);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, 0, -2);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, 3, 0);

        galaxia.simularHasta(1);

        assertEquals(1, galaxia.getMapEventoCantidad().get(EventoGalaxia.PERIODO_LLUVIA), 0d);
        assertEquals(1, galaxia.getDiaPeriodoMaximo(), 0d);
        assertEquals(11.21, galaxia.getPerimetroMaximo(), 1d);
    }

    private static void assertCoordenadas(final int angulo, final Planeta planeta) {
        assertEquals((int) (Math.cos(Math.toRadians(angulo)) * planeta.getDistanciaAlSol()), planeta.getPosicion().getX(), 0d);
        assertEquals((int) (Math.sin(Math.toRadians(angulo)) * planeta.getDistanciaAlSol()), planeta.getPosicion().getY(), 0d);
    }

    @Test
    public void testComputarPerimetro() {
        galaxia.computarPerimetro(10d);
        galaxia.computarPerimetro(1d);
        galaxia.computarPerimetro(20d);
        assertEquals(20d, galaxia.getPerimetroMaximo(), 0d);
    }
}
