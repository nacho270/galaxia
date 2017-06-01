package com.ml.model.eventos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ml.model.Galaxia;
import com.ml.model.Planeta;
import com.ml.model.posicionamiento.rectangular.CoordenadaRectangular;
import com.ml.model.posicionamiento.rectangular.EstrategiaRectangular;

public class AccionPeriodoLluviaTest {

    private final AccionPeriodoLluvia accion = new AccionPeriodoLluvia();
    private final Galaxia galaxia = new Galaxia(new EstrategiaRectangular());

    @Before
    public void setup() {
        galaxia.getPlanetas().clear();
    }

    @Test
    public void testPerimetro() {
        final List<Planeta> planetas = new ArrayList<>();
        final Planeta p1 = new Planeta("P1", (short) 1, 1, true, new CoordenadaRectangular(0d, 2d));
        final Planeta p2 = new Planeta("P2", (short) 1, 1, true, new CoordenadaRectangular(0d, -2d));
        final Planeta p3 = new Planeta("P3", (short) 1, 1, true, new CoordenadaRectangular(2d, 0d));
        planetas.add(p1);
        planetas.add(p2);
        planetas.add(p3);
        galaxia.getPlanetas().addAll(planetas);
        assertEquals(Math.sqrt(8) * 2 + 4, accion.perimetro(galaxia), 0d);
    }
}
