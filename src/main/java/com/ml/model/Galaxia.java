package com.ml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.ml.model.posicionamiento.common.CalculadorPosicion;
import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

public class Galaxia {

    private final List<Planeta> planetas;
    private final Sol sol;
    private final CalculadorPosicion<?> calculadorPosicion;
    private final Map<EventoGalaxia, Integer> mapEventoCantidad = new HashMap<>();
    private double perimetroMaximo = Double.MIN_VALUE;
    private int diaPeriodoMaximo = 0;

    private int diaActual = 0;

    public Galaxia(final EstrategiaPosicionamiento<?, ?> estrategiaRectangular) {
        calculadorPosicion = estrategiaRectangular.getCalculadorPosicion();
        planetas = new ArrayList<>();
        sol = new Sol(calculadorPosicion.crearCoordenada(0, 0));
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
        planetas.forEach(
                p -> p.setPosicion(calculadorPosicion.calcularPosicion(diaActual, p.getVelocidadAngular(), p.getDistanciaAlSol())));

        calcularEventos();
    }

    private void calcularEventos() {
        // Eventos:
        // 1) Alineados incluido sol
        // 2) Forman triangulo incluido sol
        // 2.1) Determinar maximo perimetro
        // 3) Alineados sin incluir sol
        Stream.of(EventoGalaxia.values()).forEach(evento -> {
            if (evento.aplica(this)) {
                evento.computar(this);
                // mapEventoCantidad.compute(evento, (k, v) -> v + 1);
            }
        });
        // if (estan los 3 alineados) {
        // if(el sol tambien) {
        // Evento 1
        // } else {
        // Evento3
        // }
        // } else if(forman triangulo && incluidoSol) {
        // Evento 2
        // calcular permimetro, comparar con el maximo
        // }

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
     * @return the sol
     */
    public final Sol getSol() {
        return sol;
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
}
