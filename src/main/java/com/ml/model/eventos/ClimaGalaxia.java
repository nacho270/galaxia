package com.ml.model.eventos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modela una evento de la galaxia mediante un tipo y el dia en el que ocurrio.
 */
@Entity
@Table(name = "CLIMA", indexes = { @Index(name = "IX_DIA", columnList = "dia") })
public class ClimaGalaxia {

    @JsonIgnore
    private Integer id;
    private TipoClimaGalaxia tipoEvento;
    private Integer dia;

    /**
     * Constructor.
     */
    public ClimaGalaxia() {

    }

    /**
     * Constructor.
     *
     * @param tipoEvento
     *            {@link TipoClimaGalaxia} El tipo de evento.
     * @param dia
     *            {@link Integer} El dia en el que ocurre el evento.
     */
    public ClimaGalaxia(final TipoClimaGalaxia tipoEvento, final Integer dia) {
        this.tipoEvento = tipoEvento;
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
     * @return the tipoEvento
     */
    @Enumerated
    public final TipoClimaGalaxia getTipoEvento() {
        return tipoEvento;
    }

    /**
     * @param tipoEvento
     *            the tipoEvento to set
     */
    public final void setTipoEvento(final TipoClimaGalaxia tipoEvento) {
        this.tipoEvento = tipoEvento;
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
