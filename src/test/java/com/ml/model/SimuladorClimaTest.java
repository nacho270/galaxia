package com.ml.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.clima.TipoClimaGalaxia;
import com.ml.model.posicionamiento.cartesiano.CalculadorPosicionCartesiana;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;

public class SimuladorClimaTest {
    private SimuladorClima simuladorClima;
    private Galaxia galaxia;

    @Before
    public void init() {
        galaxia = new Galaxia(new CalculadorPosicionCartesiana());
        simuladorClima = spy(new SimuladorClima(galaxia, new EstrategiaCartesiana()));
    }

    @Test
    public void testUnDia() {
        galaxia.agregarPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.agregarPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.agregarPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);

        simuladorClima.simularHasta(1);
        assertEquals(1, simuladorClima.getDiaActual());

        final Planeta ferengi = galaxia.getPlaneta("Ferengi");
        final Planeta betasoide = galaxia.getPlaneta("Betasoide");
        final Planeta vulcano = galaxia.getPlaneta("Vulcano");

        assertCoordenadas(359, ferengi);
        assertCoordenadas(357, betasoide);
        assertCoordenadas(5, vulcano);
    }

    @Test
    public void testPlanetaRecorridoHorarioMasDeUnaVuelta() {
        galaxia.agregarPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.agregarPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.agregarPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);

        simuladorClima.simularHasta(400);
        assertEquals(400, simuladorClima.getDiaActual());
        final Planeta ferengi = galaxia.getPlaneta("Ferengi");
        assertCoordenadas(320, ferengi);
    }

    @Test
    public void testIncrementarDiaParaGenerarCOPT() {
        doNothing().when(simuladorClima).actualizarPosiciones();

        galaxia.agregarPlaneta("Ferengi", (short) 1, 500, true, -500, 350);
        galaxia.agregarPlaneta("Betasoide", (short) 3, 2000, true, -1000, 350);
        galaxia.agregarPlaneta("Vulcano", (short) 5, 1000, false, -2000, 350);

        simuladorClima.simularHasta(1);

        assertEquals(1, simuladorClima.getMapClimaCantidad().get(TipoClimaGalaxia.COPT), 0d);
    }

    @Test
    public void testIncrementarDiaParaGenerarSequia() {
        doNothing().when(simuladorClima).actualizarPosiciones();

        galaxia.agregarPlaneta("Ferengi", (short) 1, 500, true, 1, 1);
        galaxia.agregarPlaneta("Betasoide", (short) 3, 2000, true, 2, 2);
        galaxia.agregarPlaneta("Vulcano", (short) 5, 1000, false, 3, 3);

        simuladorClima.simularHasta(1);

        assertEquals(1, simuladorClima.getMapClimaCantidad().get(TipoClimaGalaxia.SEQUIA), 0d);
    }

    @Test
    public void testIncrementarDiaParaGenerarLluvia() {
        doNothing().when(simuladorClima).actualizarPosiciones();

        galaxia.agregarPlaneta("Ferengi", (short) 1, 500, true, 0, 2);
        galaxia.agregarPlaneta("Betasoide", (short) 3, 2000, true, 0, -2);
        galaxia.agregarPlaneta("Vulcano", (short) 5, 1000, false, 3, 0);

        simuladorClima.simularHasta(1);

        assertEquals(1, simuladorClima.getMapClimaCantidad().get(TipoClimaGalaxia.LLUVIA), 0d);
        assertEquals(1, simuladorClima.getDiaPicoMaximoLluvia(), 0d);
        assertEquals(11.21, simuladorClima.getPerimetroMaximo(), 1d);
    }

    private static void assertCoordenadas(final int angulo, final Planeta planeta) {
        assertEquals((int) (Math.cos(Math.toRadians(angulo)) * planeta.getDistanciaAlSol()), planeta.getPosicion().getX(), 0d);
        assertEquals((int) (Math.sin(Math.toRadians(angulo)) * planeta.getDistanciaAlSol()), planeta.getPosicion().getY(), 0d);
    }

    @Test
    public void testComputarPerimetro() {
        simuladorClima.computarPerimetro(10d);
        simuladorClima.computarPerimetro(1d);
        simuladorClima.computarPerimetro(20d);
        assertEquals(20d, simuladorClima.getPerimetroMaximo(), 0d);
    }
}
