package com.ml.model.eventos.integracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.Galaxia;
import com.ml.model.eventos.AccionCOPT;
import com.ml.model.posicionamiento.cartesiano.CalculadorPosicionCartesiana;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;

public class AccionCOPTIntegrationTest {

    private final AccionCOPT accionCOPT = new AccionCOPT();
    private final Galaxia galaxia = new Galaxia(new EstrategiaCartesiana());

    @Before
    public void setup() {
        galaxia.getPlanetas().clear();
    }

    @Test
    public void testAplicaTrue() {
        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, -500, 350);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, -1000, 350);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, -2000, 350);

        assertTrue(accionCOPT.aplica(galaxia, new CalculadorPosicionCartesiana()));
    }

    @Test
    public void testAplicaFalse() {
        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);

        assertFalse(accionCOPT.aplica(galaxia, new CalculadorPosicionCartesiana()));
    }
}
