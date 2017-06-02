package com.ml.model.clima;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modela un clima de la galaxia mediante un tipo y el dia en el que ocurrio.
 */
@Entity
@Table(name = "CLIMA", indexes = { @Index(name = "IX_DIA", columnList = "dia") })
public class ClimaGalaxia {

    @JsonIgnore
    private Integer id;
    private TipoClimaGalaxia clima;
    private Integer dia;

    /**
     * Constructor.
     */
    public ClimaGalaxia() {

    }

    /**
     * Constructor.
     *
     * @param tipoClima
     *            {@link TipoClimaGalaxia} El tipo de clima.
     * @param dia
     *            {@link Integer} El dia en el que ocurre el tipo de clima.
     */
    public ClimaGalaxia(final TipoClimaGalaxia tipoClima, final Integer dia) {
        clima = tipoClima;
        this.dia = dia;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue
    public final Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return the tipoClima
     */
    @Enumerated
    public final TipoClimaGalaxia getClima() {
        return clima;
    }

    /**
     * @param tipoClima
     *            the tipoClima to set
     */
    public final void setClima(final TipoClimaGalaxia tipoClima) {
        clima = tipoClima;
    }

    /**
     * @return the dia
     */
    @Column(name = "dia", nullable = false)
    public final Integer getDia() {
        return dia;
    }

    /**
     * @param dia
     *            the dia to set
     */
    public final void setDia(final Integer dia) {
        this.dia = dia;
    }
}
