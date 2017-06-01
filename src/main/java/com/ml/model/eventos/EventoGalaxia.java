package com.ml.model.eventos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "EVENTO", indexes = { @Index(name = "IX_DIA", columnList = "dia") })
public class EventoGalaxia {

    @JsonIgnore
    private Integer id;
    private TipoEventoGalaxia tipoEvento;
    private Integer dia;

    public EventoGalaxia() {

    }

    public EventoGalaxia(final TipoEventoGalaxia tipoEvento, final Integer dia) {
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
    public final TipoEventoGalaxia getTipoEvento() {
        return tipoEvento;
    }

    /**
     * @param tipoEvento
     *            the tipoEvento to set
     */
    public final void setTipoEvento(final TipoEventoGalaxia tipoEvento) {
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
