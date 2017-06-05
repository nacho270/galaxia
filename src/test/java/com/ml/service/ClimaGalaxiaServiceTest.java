package com.ml.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.ml.exception.ParametroRequeridoExceptionException;
import com.ml.model.clima.ClimaGalaxia;
import com.ml.repository.ClimaGalaxiaRepository;
import com.ml.repository.EstadisticasClimaRepository;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = GalaxiaService.class)
public class ClimaGalaxiaServiceTest {

    @Autowired
    @InjectMocks
    private ClimaGalaxiaService climaGalaxiaService;

    @Mock
    private ClimaGalaxiaRepository climaGalaxiaRepository;

    @Mock
    private EstadisticasClimaRepository estadisticasClimaRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testClimaParaDiaNoExistente() {
        when(climaGalaxiaRepository.findByDia(10)).thenReturn(null);
        assertNull(climaGalaxiaService.climaParaDia(10));
    }

    @Test
    public void testClimaParaDiaExistente() {
        when(climaGalaxiaRepository.findByDia(10)).thenReturn(new ClimaGalaxia());
        assertNotNull(climaGalaxiaService.climaParaDia(10));
    }

    @Test(expected = ParametroRequeridoExceptionException.class)
    public void testClimaParaDiaNull() {
        climaGalaxiaService.climaParaDia(null);
    }
}
