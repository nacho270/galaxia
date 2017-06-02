package com.ml.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.ml.model.ClimaCantidad;
import com.ml.model.EstadisticasClima;
import com.ml.model.Galaxia;
import com.ml.model.clima.ClimaGalaxia;
import com.ml.model.clima.TipoClimaGalaxia;
import com.ml.repository.GalaxiaRepository;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = GalaxiaService.class)
public class GalaxiaServiceTest {

    @Autowired
    @InjectMocks
    private GalaxiaService galaxiaService;

    @Mock
    private GalaxiaRepository galaxiaRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEstadisticas() {
        final Galaxia galaxia = new Galaxia();
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.LLUVIA, 1));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.LLUVIA, 2));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.LLUVIA, 3));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.LLUVIA, 4));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.COPT, 5));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.COPT, 6));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 7));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 8));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 9));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 10));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.SEQUIA, 11));
        galaxia.getClimas().add(new ClimaGalaxia(TipoClimaGalaxia.NORMAL, 12));

        when(galaxiaRepository.findAll()).thenReturn(Arrays.asList(galaxia));

        final EstadisticasClima estadisticas = galaxiaService.getEstadisticas();
        assertCantidadClima(estadisticas.getEstadisticasClima(), TipoClimaGalaxia.LLUVIA, 4);
        assertCantidadClima(estadisticas.getEstadisticasClima(), TipoClimaGalaxia.COPT, 2);
        assertCantidadClima(estadisticas.getEstadisticasClima(), TipoClimaGalaxia.SEQUIA, 5);
        assertCantidadClima(estadisticas.getEstadisticasClima(), TipoClimaGalaxia.NORMAL, 1);

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
