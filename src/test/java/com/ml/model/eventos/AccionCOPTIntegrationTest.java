package com.ml.model.eventos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.Galaxia;
import com.ml.model.eventos.HandlerCOPT;
import com.ml.model.eventos.TipoEventoGalaxia;
import com.ml.model.posicionamiento.cartesiano.CalculadorPosicionCartesiana;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;

public class AccionCOPTIntegrationTest {

    private final HandlerCOPT accionCOPT = new HandlerCOPT();
    private final Galaxia galaxia = new Galaxia(new EstrategiaCartesiana());

    @Before
    public void setup() {
        galaxia.getPlanetas().clear();
    }

    @Test
    public void testAplicaTrue() {
        galaxia.agregarPlaneta("Ferengi", (short) 1, 500, true, -500, 350);
        galaxia.agregarPlaneta("Betasoide", (short) 3, 2000, true, -1000, 350);
        galaxia.agregarPlaneta("Vulcano", (short) 5, 1000, false, -2000, 350);

        assertTrue(accionCOPT.aplica(galaxia, new CalculadorPosicionCartesiana()));
        accionCOPT.computar(galaxia);
        assertEquals(1, galaxia.getMapEventoCantidad().get(TipoEventoGalaxia.COPT), 0d);
        accionCOPT.computar(galaxia);
        assertEquals(2, galaxia.getMapEventoCantidad().get(TipoEventoGalaxia.COPT), 0d);
    }

    @Test
    public void testAplicaFalse() {
        galaxia.agregarPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.agregarPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.agregarPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);

        assertFalse(accionCOPT.aplica(galaxia, new CalculadorPosicionCartesiana()));
    }
}
