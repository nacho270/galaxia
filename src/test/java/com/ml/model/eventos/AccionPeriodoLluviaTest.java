package com.ml.model.eventos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.Galaxia;
import com.ml.model.Planeta;
import com.ml.model.posicionamiento.cartesiano.CalculadorPosicionCartesiana;
import com.ml.model.posicionamiento.cartesiano.CoordenadaCartesiana;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

public class AccionPeriodoLluviaTest {

    private final HandlerClimaLluvia accionPeriodoLluvia = new HandlerClimaLluvia();
    private EstrategiaCartesiana estrategiaCartesiana;
    private final CalculadorPosicionCartesiana calculadorCartesiano = mock(CalculadorPosicionCartesiana.class);
    private Galaxia galaxia;

    @Before
    public void setup() {
        estrategiaCartesiana = mock(EstrategiaCartesiana.class);
        when(estrategiaCartesiana.getCalculadorPosicion()).thenReturn(calculadorCartesiano);
        galaxia = new Galaxia(estrategiaCartesiana);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testNoEstanAlineadosYDistribucionContieneAlSol() {
        when(calculadorCartesiano.estanAlineados(anyList())).thenReturn(false);
        when(calculadorCartesiano.distribucionPlanetasContieneSol(anyList(), any(CoordenadaBidimensional.class))).thenReturn(true);
        assertTrue(accionPeriodoLluvia.aplica(galaxia, calculadorCartesiano));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testNoEstanAlineadosYDistribucionNoContieneAlSol() {
        when(calculadorCartesiano.estanAlineados(anyList())).thenReturn(false);
        when(calculadorCartesiano.distribucionPlanetasContieneSol(anyList(), any(CoordenadaBidimensional.class))).thenReturn(false);
        assertFalse(accionPeriodoLluvia.aplica(galaxia, calculadorCartesiano));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testNoAlieneados() {
        when(calculadorCartesiano.estanAlineados(anyList())).thenReturn(false);
        assertFalse(accionPeriodoLluvia.aplica(galaxia, calculadorCartesiano));
    }

    @Test
    public void testPerimetro() {
        final List<Planeta> planetas = new ArrayList<>();
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaCartesiana(0d, 2d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaCartesiana(0d, -2d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaCartesiana(2d, 0d));
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);
        galaxia.getPlanetas().addAll(planetas);
        assertEquals(Math.sqrt(8) * 2 + 4, accionPeriodoLluvia.perimetro(galaxia), 0d);
    }
}
