package com.ml.model.eventos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.cartesiano.CalculadorPosicionCartesiana;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

public class AccionPeriodoSequiaTest {

    private final AccionPeriodoSequia accionPeriodoSequia = new AccionPeriodoSequia();
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
    public void testAlieneados() {
        when(calculadorCartesiano.estanAlineadosConElSol(anyList(), any(CoordenadaBidimensional.class))).thenReturn(true);
        assertTrue(accionPeriodoSequia.aplica(galaxia, calculadorCartesiano));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testNoAlieneados() {
        when(calculadorCartesiano.estanAlineadosConElSol(anyList(), any(CoordenadaBidimensional.class))).thenReturn(false);
        assertFalse(accionPeriodoSequia.aplica(galaxia, calculadorCartesiano));
    }
}
