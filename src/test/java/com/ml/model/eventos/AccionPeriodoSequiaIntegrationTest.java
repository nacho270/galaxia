package com.ml.model.eventos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.Galaxia;
import com.ml.model.Planeta;
import com.ml.model.eventos.HandlerPeriodoSequia;
import com.ml.model.eventos.TipoEventoGalaxia;
import com.ml.model.posicionamiento.cartesiano.CalculadorPosicionCartesiana;
import com.ml.model.posicionamiento.cartesiano.CoordenadaCartesiana;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;

public class AccionPeriodoSequiaIntegrationTest {

    private final HandlerPeriodoSequia accionPeriodoSequia = new HandlerPeriodoSequia();
    private final Galaxia galaxia = new Galaxia(new EstrategiaCartesiana());

    @Before
    public void setup() {
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

        accionPeriodoSequia.computar(galaxia);
        assertEquals(1, galaxia.getMapEventoCantidad().get(TipoEventoGalaxia.PERIODO_SEQUIA), 0d);
        accionPeriodoSequia.computar(galaxia);
        assertEquals(2, galaxia.getMapEventoCantidad().get(TipoEventoGalaxia.PERIODO_SEQUIA), 0d);
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
