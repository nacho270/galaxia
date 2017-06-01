package com.ml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

@Entity
@Table(name = "GALAXIA")
public class Galaxia {

    private short id;
    private List<Planeta> planetas;
    private CalculadorPosicion<?> calculadorPosicion;
    final Map<EventoGalaxia, Integer> mapEventoCantidad = new HashMap<>();
    private CoordenadaBidimensional coordenadasSol;
    private double perimetroMaximo = Double.MIN_VALUE;
    private int diaPeriodoMaximo = 0;
    private int diaActual = 0;

    public Galaxia() {
        planetas = new ArrayList<>();
        calculadorPosicion = new CalculadorPosicionCartesiana();
        coordenadasSol = calculadorPosicion.crearCoordenada(0, 0);
    }

    public Galaxia(final EstrategiaPosicionamiento<?, ?> estrategia) {
        calculadorPosicion = estrategia.getCalculadorPosicion();
        // igual en coordenadas rectangulares y polares
        coordenadasSol = calculadorPosicion.crearCoordenada(0, 0);
        planetas = new ArrayList<>();
        Stream.of(EventoGalaxia.values()).forEach(e -> mapEventoCantidad.put(e, 0));
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
     * @return the mapEventoCantidad
     */
    @Transient
    public final Map<EventoGalaxia, Integer> getMapEventoCantidad() {
        return mapEventoCantidad;
    }

    /**
     * @return the coordenadasSol
     */
    @Embedded
    @AttributeOverrides({ //
            @AttributeOverride(name = "x", column = @Column(name = "X_SOL")), //
            @AttributeOverride(name = "y", column = @Column(name = "y_SOL")), //
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

    public void addPlaneta(final String nombre, final short velocidadAngular, final int distanciaAlSol, final boolean horaria,
            final int x, final int y) {
        planetas.add(new Planeta(nombre, velocidadAngular, distanciaAlSol, horaria, calculadorPosicion.crearCoordenada(x, y)));
    }

    public void simularHasta(final int cantidadDias) {
        for (int i = 0; i < cantidadDias; i++) {
            incrementarDia();
        }
    }

    private void incrementarDia() {
        diaActual++;
        actualizarPosiciones();
        actualizarEventos();
    }

    protected void actualizarPosiciones() {
        planetas.forEach(
                p -> p.setPosicion(calculadorPosicion.calcularPosicion(diaActual, p.getVelocidadAngular(), p.getDistanciaAlSol())));
    }

    private void actualizarEventos() {
        // Eventos:
        // 1) Alineados incluido sol
        // 2) Forman triangulo incluido sol
        // 2.1) Determinar maximo perimetro
        // 3) Alineados sin incluir sol
        Stream.of(EventoGalaxia.values()).forEach(evento -> {
            if (evento.aplica(this, calculadorPosicion)) {
                evento.computar(this);
            }
        });
    }

    public void sumarEvento(final EventoGalaxia periodoLluvia) {
        mapEventoCantidad.compute(periodoLluvia, (k, v) -> v + 1);
    }

    public void computarPerimetro(final double perimetro) {
        if (perimetro > perimetroMaximo) {
            perimetroMaximo = perimetro;
            diaPeriodoMaximo = diaActual;
        }
    }

    // solo para test
    protected Planeta getPlaneta(final String nombre) {
        for (final Planeta p : planetas) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }
}
