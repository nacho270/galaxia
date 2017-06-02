package com.ml.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ml.model.clima.ClimaGalaxia;
import com.ml.model.clima.TipoClimaGalaxia;

public class EstadisticasClimaTest {

    @Test
    public void testEstadisticas() {
        final List<ClimaGalaxia> climas = new ArrayList<>();
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.LLUVIA, 1));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.LLUVIA, 2));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.LLUVIA, 3));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.LLUVIA, 4));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.COPT, 5));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.COPT, 6));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 7));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 8));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 9));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 10));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 11));
        climas.add(new ClimaGalaxia(TipoClimaGalaxia.NORMAL, 12));
        final EstadisticasClima estadisticas = new EstadisticasClima(10, climas);
        assertCantidadClima(estadisticas.getClimasCantidad(), TipoClimaGalaxia.LLUVIA, 4);
        assertCantidadClima(estadisticas.getClimasCantidad(), TipoClimaGalaxia.COPT, 2);
        assertCantidadClima(estadisticas.getClimasCantidad(), TipoClimaGalaxia.SEQUIA, 5);
        assertCantidadClima(estadisticas.getClimasCantidad(), TipoClimaGalaxia.NORMAL, 1);
    }

    private static void assertCantidadClima(final List<ClimaCantidad> estadisticasClima, final TipoClimaGalaxia clima,
                    final int cantidadAProbar) {
        ClimaCantidad climaCantidad = null;
        for (final ClimaCantidad cc : estadisticasClima) {
            if (cc.getClima() == clima) {
                climaCantidad = cc;
                break;
            }
        }
        assertNotNull(climaCantidad);
        assertEquals(cantidadAProbar, climaCantidad.getCantidad(), 0d);
    }
}
