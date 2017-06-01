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

public class AccionCOPTTest {

    private final AccionCOPT accionCOPT = new AccionCOPT();
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
    public void testAlieneadosEntreSiYNoConElSol() {
        when(calculadorCartesiano.estanAlineados(anyList())).thenReturn(true);
        when(calculadorCartesiano.estanAlineadosConElSol(anyList(), any(CoordenadaBidimensional.class))).thenReturn(false);
        assertTrue(accionCOPT.aplica(galaxia, calculadorCartesiano));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAlieneadosEntreSiYConElSol() {
        when(calculadorCartesiano.estanAlineados(anyList())).thenReturn(true);
        when(calculadorCartesiano.estanAlineadosConElSol(anyList(), any(CoordenadaBidimensional.class))).thenReturn(true);
        assertFalse(accionCOPT.aplica(galaxia, calculadorCartesiano));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testNoAlieneadosEntreSiYSiConElSol() {
        when(calculadorCartesiano.estanAlineados(anyList())).thenReturn(false);
        when(calculadorCartesiano.estanAlineadosConElSol(anyList(), any(CoordenadaBidimensional.class))).thenReturn(true);
        assertFalse(accionCOPT.aplica(galaxia, calculadorCartesiano));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testNoAlieneadosEntreSiYTampocoConElSol() {
        when(calculadorCartesiano.estanAlineados(anyList())).thenReturn(false);
        when(calculadorCartesiano.estanAlineadosConElSol(anyList(), any(CoordenadaBidimensional.class))).thenReturn(false);
        assertFalse(accionCOPT.aplica(galaxia, calculadorCartesiano));
    }
}
