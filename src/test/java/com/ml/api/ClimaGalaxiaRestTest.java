package com.ml.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.ml.model.EstadisticasClima;
import com.ml.model.clima.ClimaGalaxia;
import com.ml.service.ClimaGalaxiaService;
import com.ml.service.GalaxiaService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = GalaxiaService.class)
public class ClimaGalaxiaRestTest {

    @Autowired
    @InjectMocks
    private ClimaGalaxiaRest climaGalaxiaRest;

    @Mock
    private ClimaGalaxiaService climaGalaxiaService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testClimaParaDiaNoExistente() {
        when(climaGalaxiaService.climaParaDia(10)).thenReturn(null);
        assertNull(climaGalaxiaService.climaParaDia(10));
    }

    @Test
    public void testClimaParaDiaExistente() {
        when(climaGalaxiaService.climaParaDia(10)).thenReturn(new ClimaGalaxia());
        assertNotNull(climaGalaxiaService.climaParaDia(10));
    }

    @Test
    public void testGetEstadisticasNoExistentes() {
        when(climaGalaxiaService.getEstadisticas()).thenReturn(null);
        assertNull(climaGalaxiaRest.estadisticas());
    }

    @Test
    public void testGetEstadisticasExistentes() {
        when(climaGalaxiaService.getEstadisticas()).thenReturn(new EstadisticasClima(10, new ArrayList<>()));
        assertNotNull(climaGalaxiaRest.estadisticas());
    }
}
