package com.ml.exception;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handler global de excepciones.<br>
 * Retorna un {@link Map} con la key "error" y el mensaje de error como valor.
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    protected static final String ERROR_KEY = "error";

    /**
     * Handler para {@link ParametroRequeridoExceptionException}
     *
     * @param excepcion
     *            {@link ParametroRequeridoExceptionException} La excepcion de
     *            parametro faltante.
     * @return {@link Map} El mapa con la key "error" y el mensaje como valor.
     */
    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handle(final ParametroRequeridoExceptionException excepcion) {
        return error(excepcion.getMessage());
    }

    /**
     * Handler por defecto.
     *
     * @param excepcion
     *            {@link Throwable} Una excepcion no contemplada.
     * @return {@link Map} El mapa con la key "error" y el mensaje como valor.
     */
    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handle(final Throwable excepcion) {
        LOGGER.debug("", excepcion);
        return error("Se ha producido un error");
    }

    /**
     * Crea el {@link Map} de respuesta.
     *
     * @param mensaje
     *            {@link String} El mensaje de error a retornar.
     * @return {@link Map} El mapa con la key "error" y el mensaje como valor.
     */
    private static Map<String, Object> error(final String mensaje) {
        return Collections.singletonMap(ERROR_KEY, mensaje);
    }
}
