package com.ml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.ml.model.posicionamiento.common.CalculadorPosicion;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;
import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

public class Galaxia {

    private final List<Planeta> planetas;
    private final CalculadorPosicion<?> calculadorPosicion;
    private final Map<EventoGalaxia, Integer> mapEventoCantidad = new HashMap<>();
    private final CoordenadaBidimensional coordenadasSol;

    private double perimetroMaximo = Double.MIN_VALUE;

    /**
     * @return the perimetroMaximo
     */
    protected double getPerimetroMaximo() {
        return perimetroMaximo;
    }

    private int diaPeriodoMaximo = 0;
    private int diaActual = 0;

    public Galaxia(final EstrategiaPosicionamiento<?, ?> estrategiaRectangular) {
        calculadorPosicion = estrategiaRectangular.getCalculadorPosicion();
        // igual en coordenadas rectangulares y polares
        coordenadasSol = calculadorPosicion.crearCoordenada(0, 0);
        planetas = new ArrayList<>();
        Stream.of(EventoGalaxia.values()).forEach(e -> mapEventoCantidad.put(e, 0));
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

    private void actualizarPosiciones() {
        planetas.forEach(p -> p.setPosicion(
                        calculadorPosicion.calcularPosicion(diaActual, p.getVelocidadAngular(), p.getDistanciaAlSol())));
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

    /**
     * @return the planetas
     */
    public final List<Planeta> getPlanetas() {
        return planetas;
    }

    /**
     * @return the diaActual
     */
    public final int getDiaActual() {
        return diaActual;
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

    /**
     * @return the mapEventoCantidad
     */
    public final Map<EventoGalaxia, Integer> getMapEventoCantidad() {
        return mapEventoCantidad;
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

    /**
     * @return the diaPeriodoMaximo
     */
    public final int getDiaPeriodoMaximo() {
        return diaPeriodoMaximo;
    }

    public CoordenadaBidimensional getCoordenadasSol() {
        return coordenadasSol;
    }
}
