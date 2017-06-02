package com.ml.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Target;

import com.ml.model.posicionamiento.cartesiano.CalculadorPosicionCartesiana;
import com.ml.model.posicionamiento.cartesiano.CoordenadaCartesiana;
import com.ml.model.posicionamiento.common.CalculadorPosicion;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

/**
 * Almacena la configuracion de una galaxia teniendo en el punto (0,0) al sol y
 * almacenando la cuenta de tipo de clima, y los climas en si, durante la
 * simulacion.
 */
@Entity
@Table(name = "GALAXIA")
public class Galaxia {

    private short id;
    private List<Planeta> planetas;
    private CoordenadaBidimensional coordenadasSol;
    private final CalculadorPosicion<?> calculadorPosicion;

    /**
     * Constructor. Utiliza un {@link CalculadorPosicionCartesiana} por defecto.
     */
    public Galaxia() {
        calculadorPosicion = new CalculadorPosicionCartesiana();
        planetas = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param calculadorPosicion
     *            {@link CalculadorPosicion} El calculador de posicion para
     *            posicionar al sol.
     */
    public Galaxia(final CalculadorPosicion<?> calculadorPosicion) {
        planetas = new ArrayList<>();
        coordenadasSol = calculadorPosicion.crearCoordenada(0, 0);
        this.calculadorPosicion = calculadorPosicion;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue
    public final short getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final short id) {
        this.id = id;
    }

    /**
     * @return the planetas
     */
    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "F_GALAXIA_ID")
    public final List<Planeta> getPlanetas() {
        return planetas;
    }

    /**
     * @param planetas
     *            the planetas to set
     */
    protected final void setPlanetas(final List<Planeta> planetas) {
        this.planetas = planetas;
    }

    /**
     * @return the coordenadasSol
     */
    @Embedded
    @AttributeOverrides({ //
                    @AttributeOverride(name = "x", column = @Column(name = "X_SOL")), //
                    @AttributeOverride(name = "y", column = @Column(name = "Y_SOL")), //
    })
    @Target(CoordenadaCartesiana.class)
    public final CoordenadaBidimensional getCoordenadasSol() {
        return coordenadasSol;
    }

    /**
     * @param coordenadasSol
     *            the coordenadasSol to set
     */
    protected final void setCoordenadasSol(final CoordenadaBidimensional coordenadasSol) {
        this.coordenadasSol = coordenadasSol;
    }

    /**
     * Agrega un planeta a la galaxia.
     *
     * @param nombre
     *            {@link String} El nombre del planeta.
     * @param velocidadAngular
     *            {@link Short} La velocidad angular del planeta.
     * @param distanciaAlSol
     *            {@link Integer} La distancia al sol del planeta.
     * @param horaria
     *            {@link Boolean} Si el planeta se mueve en sentido horario o
     *            no.
     * @param x
     *            {@link Integer} Coordenada X.
     * @param y
     *            {@link Integer} Coordenada Y.
     */
    public void agregarPlaneta(final String nombre, final short velocidadAngular, final int distanciaAlSol, final boolean horaria,
                    final int x, final int y) {
        planetas.add(new Planeta(nombre, velocidadAngular, distanciaAlSol, horaria, calculadorPosicion.crearCoordenada(x, y)));
    }

    /**
     * SOLO PARA TEST. Busca un planeta por nombre.
     *
     * @param nombre
     *            {@link String} El nombre del planeta a buscar.
     * @return {@link Planeta} El planeta requerido.
     */
    @Transient
    protected Planeta getPlaneta(final String nombre) {
        return planetas.stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null);
    }
}
