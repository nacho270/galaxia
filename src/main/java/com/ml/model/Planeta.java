package com.ml.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

@Entity
@Table(name = "PLANETA")
public class Planeta {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "NOMBRE")
    private final String nombre;

    @Column(name = "VELOCIDAD_ANGULAR")
    private final short velocidadAngular;

    @Column(name = "DISTANCIA_SOL")
    private final int distanciaAlSol;

    @Embedded
    private CoordenadaBidimensional posicion;

    public Planeta(final String nombre, final short velocidadAngular, final int distanciaAlSol, final boolean horaria,
            final CoordenadaBidimensional posicionInicial) {
        this.nombre = nombre;
        this.distanciaAlSol = distanciaAlSol;
        this.velocidadAngular = (short) ((horaria ? -1 : 1) * velocidadAngular);
        posicion = posicionInicial;
    }

    /**
     * @return the nombre
     */
    public final String getNombre() {
        return nombre;
    }

    /**
     * @return the velocidadAngular
     */
    public final short getVelocidadAngular() {
        return velocidadAngular;
    }

    /**
     * @return the posicion
     */
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
    public final int getDistanciaAlSol() {
        return distanciaAlSol;
    }

    public double distancia(final Planeta planeta) {
        return getPosicion().distancia(planeta);
    }
}
