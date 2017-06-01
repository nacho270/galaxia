package com.ml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Target;

import com.ml.model.eventos.EventoGalaxia;
import com.ml.model.eventos.EventosHandlerChain;
import com.ml.model.eventos.TipoEventoGalaxia;
import com.ml.model.posicionamiento.cartesiano.CoordenadaCartesiana;
import com.ml.model.posicionamiento.cartesiano.EstrategiaCartesiana;
import com.ml.model.posicionamiento.common.CalculadorPosicion;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;
import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

/**
 * Almacena la configuracion de una galaxia teniendo en el punto (0,0) al sol y almacenando la cuenta de tipo de eventos,
 * y los eventos en si, durante la simulacion.
 */
@Entity
@Table(name = "GALAXIA")
public class Galaxia {

    private short id;
    private List<Planeta> planetas;
    private List<EventoGalaxia> eventos;
    private CalculadorPosicion<?> calculadorPosicion;
    private CoordenadaBidimensional coordenadasSol;

    private double perimetroMaximo = Double.MIN_VALUE;
    private int diaPeriodoMaximo = 0;
    private int diaActual = 0;
    private final Map<TipoEventoGalaxia, Integer> mapEventoCantidad = new HashMap<>();
    private static final EventosHandlerChain EVENTOS_CHAIN = new EventosHandlerChain();

    /**
     * Constructor.
     */
    public Galaxia() {
        this(new EstrategiaCartesiana());
    }

    /**
     * Constructor.
     *
     * @param estrategia
     *            {@link EstrategiaPosicionamiento} La estrategia de posicionamiento a utlizar.
     */
    public Galaxia(final EstrategiaPosicionamiento<?, ?> estrategia) {
        planetas = new ArrayList<>();
        eventos = new ArrayList<>();
        calculadorPosicion = estrategia.getCalculadorPosicion();
        coordenadasSol = calculadorPosicion.crearCoordenada(0, 0);
        Stream.of(TipoEventoGalaxia.values()).filter(Predicate.isEqual(TipoEventoGalaxia.NORMAL).negate())
                .forEach(e -> mapEventoCantidad.put(e, 0));
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
     * @return the eventos
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "F_GALAXIA_ID")
    public final List<EventoGalaxia> getEventos() {
        return eventos;
    }

    /**
     * @param eventos
     *            the eventos to set
     */
    public final void setEventos(final List<EventoGalaxia> eventos) {
        this.eventos = eventos;
    }

    /**
     * @return the mapEventoCantidad
     */
    @Transient
    public final Map<TipoEventoGalaxia, Integer> getMapEventoCantidad() {
        return mapEventoCantidad;
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
     * @return the perimetroMaximo
     */
    @Transient
    public final double getPerimetroMaximo() {
        return perimetroMaximo;
    }

    /**
     * @return the diaPeriodoMaximo
     */
    @Transient
    public final int getDiaPeriodoMaximo() {
        return diaPeriodoMaximo;
    }

    /**
     * @return the diaActual
     */
    @Transient
    public final int getDiaActual() {
        return diaActual;
    }

    /**
     * @param calculadorPosicion
     *            the calculadorPosicion to set
     */
    public final void setCalculadorPosicion(final CalculadorPosicion<?> calculadorPosicion) {
        this.calculadorPosicion = calculadorPosicion;
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
     *            {@link Boolean} Si el planeta se mueve en sentido horario o no.
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
     * Simula el comportamiento de la galaxia durante un determinado numero de dias.
     *
     * @param cantidadDias
     *            {@link Integer} La cantidad de dias a simular.
     */
    public void simularHasta(final int cantidadDias) {
        for (int i = 0; i < cantidadDias; i++) {
            incrementarDia();
        }
    }

    /**
     * Incrementa un dia en la galaxia, reubica los planetas y determina los eventos para la nueva posicion.
     */
    private void incrementarDia() {
        diaActual++;
        actualizarPosiciones();
        actualizarEventos();
    }

    /**
     * Reubica los planetas.
     */
    protected void actualizarPosiciones() {
        planetas.forEach(
                p -> p.setPosicion(calculadorPosicion.calcularPosicion(diaActual, p.getVelocidadAngular(), p.getDistanciaAlSol())));
    }

    /**
     * Determina los eventos para la nueva posicion.
     */
    private void actualizarEventos() {
        // Eventos:
        // 1) Alineados incluido sol
        // 2) Forman triangulo incluido sol
        // ------ 2.1) Determinar maximo perimetro
        // 3) Alineados sin incluir sol
        // 4) Dia normal
        EVENTOS_CHAIN.ejecutar(this, calculadorPosicion);
    }

    /**
     * Almacena un nuevo evento ocurrido en la galaxia: Suma su ocurrencia y lo agrega a la lista de eventos.
     *
     * @param tipoEvento
     *            {@link TipoEventoGalaxia} El tipo de evento ocurrido.
     */
    public void sumarEvento(final TipoEventoGalaxia tipoEvento) {
        mapEventoCantidad.computeIfPresent(tipoEvento, (k, v) -> v + 1);
        eventos.add(new EventoGalaxia(tipoEvento, diaActual));
    }

    /**
     * Guarda un nuevo perimetro si es mayor al ultimo guardado y actualiza el dia en el que ocurrio.
     *
     * @param perimetro
     *            {@link Double} El perimetro a computar.
     */
    public void computarPerimetro(final double perimetro) {
        if (perimetro > perimetroMaximo) {
            perimetroMaximo = perimetro;
            diaPeriodoMaximo = diaActual;
        }
    }

    /**
     * SOLO PARA TEST. Busca un planeta por nombre.
     *
     * @param nombre
     *            {@link String} El nombre del planeta a buscar.
     * @return {@link Planeta} El planeta requerido.
     */
    protected Planeta getPlaneta(final String nombre) {
        for (final Planeta p : planetas) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }
}
