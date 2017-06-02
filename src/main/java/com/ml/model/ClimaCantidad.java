package com.ml.model;

import com.ml.model.clima.TipoClimaGalaxia;

/**
 * Transfer object de tipo de clima y cantidad de ocurrencias.
 */
public class ClimaCantidad {

    private TipoClimaGalaxia clima;
    private Integer cantidad;

    /**
     * Constructor.
     *
     * @param clima
     *            {@link TipoClimaGalaxia} Tipo de clima.
     * @param cantidad
     *            {@link Integer} Cantidad de ocurrencias.
     */
    public ClimaCantidad(final TipoClimaGalaxia clima, final Integer cantidad) {
        this.clima = clima;
        this.cantidad = cantidad;
    }

    /**
     * @return the clima
     */
    public TipoClimaGalaxia getClima() {
        return clima;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param clima
     *            the clima to set
     */
    public void setClima(final TipoClimaGalaxia clima) {
        this.clima = clima;
    }

    /**
     * @param cantidad
     *            the cantidad to set
     */
    public void setCantidad(final Integer cantidad) {
        this.cantidad = cantidad;
    }
}
