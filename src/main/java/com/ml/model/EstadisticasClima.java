package com.ml.model;

import java.util.List;

/**
 * Transfer object sobre las estadisticas del pronostico generado.
 */
public class EstadisticasClima {

    private Integer diaPicoMaximoLluvia;
    private List<ClimaCantidad> estadisticasClima;

    /**
     * Constructor.
     *
     * @param diaPicoMaximoLluvia
     *            {@link Integer} Dia donde ocurrio el pico maximo de lluvia.
     * @param estadisticasClima
     *            {@link List} Lista de tipo de clima y cantidad.
     */
    public EstadisticasClima(final Integer diaPicoMaximoLluvia, final List<ClimaCantidad> estadisticasClima) {
        this.diaPicoMaximoLluvia = diaPicoMaximoLluvia;
        this.estadisticasClima = estadisticasClima;
    }

    /**
     * @return the diaPicoMaximoLluvia
     */
    public Integer getDiaPicoMaximoLluvia() {
        return diaPicoMaximoLluvia;
    }

    /**
     * @return the estadisticasClima
     */
    public List<ClimaCantidad> getEstadisticasClima() {
        return estadisticasClima;
    }

    /**
     * @param diaPicoMaximoLluvia
     *            the diaPicoMaximoLluvia to set
     */
    public void setDiaPicoMaximoLluvia(final Integer diaPicoMaximoLluvia) {
        this.diaPicoMaximoLluvia = diaPicoMaximoLluvia;
    }

    /**
     * @param estadisticasClima
     *            the estadisticasClima to set
     */
    public void setEstadisticasClima(final List<ClimaCantidad> estadisticasClima) {
        this.estadisticasClima = estadisticasClima;
    }
}
