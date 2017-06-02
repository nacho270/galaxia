package com.ml.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ml.model.clima.ClimaGalaxia;

/**
 * Contiene estadisticas del pronostico generado.
 */
@Entity
@Table(name = "ESTADISTICAS")
public class EstadisticasClima {

    @JsonIgnore
    private Integer id;
    private Integer diaPicoMaximoLluvia;
    private List<ClimaCantidad> climasCantidad;

    @JsonIgnore
    private List<ClimaGalaxia> climas;

    /**
     * Constructor.
     */
    protected EstadisticasClima() {
        climas = new ArrayList<>();
        climasCantidad = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param diaPicoMaximoLluvia
     *            {@link Integer} Dia donde ocurrio el pico maximo de lluvia.
     * @param climas
     *            {@link List} Lista de tipo de clima y cantidad.
     */
    public EstadisticasClima(final Integer diaPicoMaximoLluvia, final List<ClimaGalaxia> climas) {
        this.diaPicoMaximoLluvia = diaPicoMaximoLluvia;
        this.climas = climas;
        climasCantidad = climas.stream() //
                        .collect(Collectors.groupingBy(clima -> clima.getClima(), Collectors.counting())) //
                        .entrySet().stream() //
                        .map(entry -> new ClimaCantidad(entry.getKey(), entry.getValue().intValue())) //
                        .collect(Collectors.toList());
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
     * @return the diaPicoMaximoLluvia
     */
    @Column(name = "DIA_PICO_MAXIMO_LLUVIA", nullable = false)
    public Integer getDiaPicoMaximoLluvia() {
        return diaPicoMaximoLluvia;
    }

    /**
     * @param diaPicoMaximoLluvia
     *            the diaPicoMaximoLluvia to set
     */
    public void setDiaPicoMaximoLluvia(final Integer diaPicoMaximoLluvia) {
        this.diaPicoMaximoLluvia = diaPicoMaximoLluvia;
    }

    /**
     * @return the climas
     */
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "F_ESTADISTICAS_ID")
    public List<ClimaGalaxia> getClimas() {
        return climas;
    }

    /**
     * @param climas
     *            the climas to set
     */
    public void setClimas(final List<ClimaGalaxia> climas) {
        this.climas = climas;
    }

    /**
     * @return the climasCantidad
     */
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "F_ESTADISTICAS_ID")
    public List<ClimaCantidad> getClimasCantidad() {
        return climasCantidad;
    }

    /**
     * @param climasCantidad
     *            the climasCantidad to set
     */
    public void setClimasCantidad(final List<ClimaCantidad> climasCantidad) {
        this.climasCantidad = climasCantidad;
    }
}
