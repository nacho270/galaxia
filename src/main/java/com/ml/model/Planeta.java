package com.ml.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Target;

import com.ml.model.posicionamiento.cartesiano.CoordenadaCartesiana;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

@Entity
@Table(name = "PLANETA")
public class Planeta {

    private int id;
    private String nombre;
    private short velocidadAngular;
    private int distanciaAlSol;
    private CoordenadaBidimensional posicion;

    public Planeta(final String nombre, final short velocidadAngular, final int distanciaAlSol, final boolean horaria,
            final CoordenadaBidimensional posicionInicial) {
        this.nombre = nombre;
        this.distanciaAlSol = distanciaAlSol;
        this.velocidadAngular = (short) ((horaria ? -1 : 1) * velocidadAngular);
        posicion = posicionInicial;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue
    public final int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    @Column(name = "NOMBRE")
    public final String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    protected final void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the velocidadAngular
     */
    @Column(name = "VELOCIDAD_ANGULAR")
    public final short getVelocidadAngular() {
        return velocidadAngular;
    }

    /**
     * @param velocidadAngular
     *            the velocidadAngular to set
     */
    protected final void setVelocidadAngular(final short velocidadAngular) {
        this.velocidadAngular = velocidadAngular;
    }

    /**
     * @return the posicion
     */
    @Embedded
    @Target(CoordenadaCartesiana.class)
    public final CoordenadaBidimensional getPosicion() {
        return posicion;
    }

    /**
     * @param posicion
     *            the posicion to set
     */
    public final void setPosicion(final CoordenadaBidimensional posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the distanciaAlSol
     */
    @Column(name = "DISTANCIA_SOL")
    public final int getDistanciaAlSol() {
        return distanciaAlSol;
    }

    /**
     * @param distanciaAlSol
     *            the distanciaAlSol to set
     */
    protected final void setDistanciaAlSol(final int distanciaAlSol) {
        this.distanciaAlSol = distanciaAlSol;
    }

    public double distancia(final Planeta planeta) {
        return getPosicion().distancia(planeta);
    }
}
