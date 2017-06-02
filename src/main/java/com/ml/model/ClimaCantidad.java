package com.ml.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ml.model.clima.TipoClimaGalaxia;

/**
 * Almacena el tipo de clima y cantidad de ocurrencias.
 */
@Entity
@Table(name = "CLIMA_CANTIDAD")
public class ClimaCantidad {

    @JsonIgnore
    private Integer id;
    private TipoClimaGalaxia clima;
    private Integer cantidad;

    /**
     * Constructor.
     */
    public ClimaCantidad() {

    }

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
     * @return the id
     */
    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return the clima
     */
    @Enumerated
    public TipoClimaGalaxia getClima() {
        return clima;
    }

    /**
     * @return the cantidad
     */
    @Column(name = "CANTIDAD", nullable = false)
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
