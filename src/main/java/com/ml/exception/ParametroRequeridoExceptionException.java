package com.ml.exception;

/**
 * Excepcion que se arroja al faltar un parametro requerido en un metodo.
 */
public class ParametroRequeridoExceptionException extends RuntimeException {

    private static final long serialVersionUID = -4779822106419658685L;

    private final String parametro;

    /**
     * Constructor.
     *
     * @param parametro
     *            {@link String} El nombre del parametro requerido.
     */
    public ParametroRequeridoExceptionException(final String parametro) {
        this.parametro = parametro;
    }

    @Override
    public String getMessage() {
        return String.format("El parametro %s es requerido", parametro);
    }
}
