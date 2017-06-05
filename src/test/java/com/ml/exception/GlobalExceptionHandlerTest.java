package com.ml.exception;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    public void testParametroRequeridoException() {
        final Map<String, Object> response = handler.handle(new ParametroRequeridoExceptionException("dia"));
        assertNotNull(response);
        assertNotNull(response.get(GlobalExceptionHandler.ERROR_KEY));
        assertTrue(response.get("error").toString().contains("dia"));
    }

    @Test
    public void testException() {
        final Map<String, Object> response = handler.handle(new Exception());
        assertNotNull(response);
        assertNotNull(response.get(GlobalExceptionHandler.ERROR_KEY));
    }
}
