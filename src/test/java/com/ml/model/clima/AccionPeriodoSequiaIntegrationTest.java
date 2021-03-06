package com.ml.model.clima;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.Galaxia;
import com.ml.model.Planeta;
import com.ml.model.SimuladorClima;
import com.ml.model.posicionamiento.cartesiano.CalculadorPosicionCartesiana;
import com.ml.model.posicionamiento.cartesiano.CoordenadaCartesiana;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;

public class AccionPeriodoSequiaIntegrationTest {

    private final HandlerClimaSequia accionPeriodoSequia = new HandlerClimaSequia();
    private final Galaxia galaxia = new Galaxia(new CalculadorPosicionCartesiana());
    private SimuladorClima simuladorClima;

    @Before
    public void setup() {
        simuladorClima = new SimuladorClima(galaxia, new EstrategiaCartesiana());
        galaxia.getPlanetas().clear();
    }

    @Test
    public void testAlineados() {
        final List<Planeta> planetas = new ArrayList<>();
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaCartesiana(1d, 1d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaCartesiana(2d, 2d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaCartesiana(3d, 3d));
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);
        galaxia.getPlanetas().addAll(planetas);
        assertTrue(accionPeriodoSequia.aplica(galaxia, new CalculadorPosicionCartesiana()));

        accionPeriodoSequia.computar(simuladorClima);
        assertEquals(1, simuladorClima.getMapClimaCantidad().get(TipoClimaGalaxia.SEQUIA), 0d);
        accionPeriodoSequia.computar(simuladorClima);
        assertEquals(2, simuladorClima.getMapClimaCantidad().get(TipoClimaGalaxia.SEQUIA), 0d);
    }

    @Test
    public void testNoAlineados() {
        final List<Planeta> planetas = new ArrayList<>();
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaCartesiana(1d, 2d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaCartesiana(1d, -2d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaCartesiana(3d, 0d));
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);
        galaxia.getPlanetas().addAll(planetas);
        assertFalse(accionPeriodoSequia.aplica(galaxia, new CalculadorPosicionCartesiana()));
    }
}
