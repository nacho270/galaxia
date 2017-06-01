package com.ml;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;

public class Main {

    private static final int UN_ANIO = 365;

    public static void main(final String[] args) {
        final Galaxia galaxia = new Galaxia(new EstrategiaCartesiana());
        galaxia.addPlaneta("Ferengi", (short) 1, 500, true, 0, 500);
        galaxia.addPlaneta("Betasoide", (short) 3, 2000, true, 0, 2000);
        galaxia.addPlaneta("Vulcano", (short) 5, 1000, false, 0, 1000);

        galaxia.simularHasta(UN_ANIO * 10);

        galaxia.getMapEventoCantidad().keySet().forEach(eg -> {
            System.out.println(String.format("Hay %d dias de %s", galaxia.getMapEventoCantidad().get(eg), eg));
        });

        System.out.println(String.format("Pico maximo de lluvia, dia %d", galaxia.getDiaPeriodoMaximo()));
    }
}
