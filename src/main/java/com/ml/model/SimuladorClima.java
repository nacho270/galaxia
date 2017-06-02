package com.ml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.ml.model.clima.ClimaGalaxia;
import com.ml.model.clima.ClimaHandlerChain;
import com.ml.model.clima.TipoClimaGalaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;
import com.ml.model.posicionamiento.common.EstrategiaPosicionamiento;

/**
 * Clase encargada de realizar la simulacion del comportamiento del clima para
 * una galaxia y una cantidad de dias.
 */
public class SimuladorClima {

    private final Galaxia galaxia;
    private double perimetroMaximo = Double.MIN_VALUE;
    private Integer diaPicoMaximoLluvia = 0;
    private int diaActual = 0;
    private final Map<TipoClimaGalaxia, Integer> mapClimaCantidad = new HashMap<>();
    private static final ClimaHandlerChain CLIMA_HANDLERS_CHAIN = new ClimaHandlerChain();
    private final CalculadorPosicion<?> calculadorPosicion;

    /**
     * Constrictor.
     *
     * @param galaxia
     *            {@link Galaxia} La galaxia sobre la cual realizar la
     *            simulacion.
     * @param estrategiaPosicionamiento
     *            {@link EstrategiaPosicionamiento} La estrategia de
     *            posicionamiento de plantas a utilizar.
     */
    public SimuladorClima(final Galaxia galaxia, final EstrategiaPosicionamiento<?, ?> estrategiaPosicionamiento) {
        this.galaxia = galaxia;
        calculadorPosicion = estrategiaPosicionamiento.getCalculadorPosicion();

        // inicializo el mapa con 0 en las cantidades para despues sumarle 1
        // ante una ocurrencia.
        Stream.of(TipoClimaGalaxia.values()).forEach(e -> mapClimaCantidad.put(e, 0));
    }

    /**
     * @return the mapClimaCantidad
     */
    public final Map<TipoClimaGalaxia, Integer> getMapClimaCantidad() {
        return mapClimaCantidad;
    }

    /**
     * @return the diaPicoMaximoLluvia
     */
    public final Integer getDiaPicoMaximoLluvia() {
        return diaPicoMaximoLluvia;
    }

    /**
     * @return the diaActual
     */
    public int getDiaActual() {
        return diaActual;
    }

    /**
     * @return the perimetroMaximo
     */
    public double getPerimetroMaximo() {
        return perimetroMaximo;
    }

    /**
     * @return the galaxia
     */
    public Galaxia getGalaxia() {
        return galaxia;
    }

    /**
     * @return the calculadorPosicion
     */
    public CalculadorPosicion<?> getCalculadorPosicion() {
        return calculadorPosicion;
    }

    /**
     * Simula el comportamiento de la galaxia durante un determinado numero de
     * dias.
     *
     * @param cantidadDias
     *            {@link Integer} La cantidad de dias a simular.
     * @return {@link EstadisticasClima} El resultado de la simulacion.
     */
    public EstadisticasClima simularHasta(final int cantidadDias) {
        final List<ClimaGalaxia> climas = new ArrayList<>();
        for (int i = 0; i < cantidadDias; i++) {
            climas.add(incrementarDia());
        }
        return new EstadisticasClima(diaPicoMaximoLluvia, climas);
    }

    /**
     * Incrementa un dia en la galaxia, reubica los planetas y determina el
     * clima para la nueva posicion.
     *
     * @return {@link ClimaGalaxia} El clima generado al aumentar el dia y re
     *         posicionar los planetas.
     */
    private ClimaGalaxia incrementarDia() {
        diaActual++;
        actualizarPosiciones();
        return determinarClima();
    }

    /**
     * Reubica los planetas.
     */
    protected void actualizarPosiciones() {
        galaxia.getPlanetas().forEach(p -> p.setPosicion(
                        calculadorPosicion.calcularPosicion(diaActual, p.getVelocidadAngular(), p.getDistanciaAlSol())));
    }

    /**
     * Determina el clima para la nueva posicion.
     *
     * @return {@link ClimaGalaxia} El clima generado al aumentar el dia y re
     *         posicionar los planetas.
     */
    private ClimaGalaxia determinarClima() {
        // Tipos de clima:
        // 1) Alineados incluido sol
        // 2) Forman triangulo incluido sol
        // ------ 2.1) Determinar maximo perimetro
        // 3) Alineados sin incluir sol
        // 4) Dia normal
        return CLIMA_HANDLERS_CHAIN.ejecutar(this);
    }

    /**
     * Almacena un nuevo clima ocurrido en la galaxia: Suma su ocurrencia y
     * genera un registro de {@link ClimaGalaxia}.
     *
     * @param tipoClima
     *            {@link TipoClimaGalaxia} El tipo de clima ocurrido.
     * @return {@link ClimaGalaxia} El clima generado.
     */
    public ClimaGalaxia sumarDiaConClima(final TipoClimaGalaxia tipoClima) {
        mapClimaCantidad.compute(tipoClima, (k, v) -> v + 1);
        return new ClimaGalaxia(tipoClima, diaActual);
    }

    /**
     * Guarda un nuevo perimetro si es mayor al ultimo guardado y actualiza el
     * dia en el que ocurrio.
     *
     * @param perimetro
     *            {@link Double} El perimetro a computar.
     */
    public void computarPerimetro(final double perimetro) {
        if (perimetro > perimetroMaximo) {
            perimetroMaximo = perimetro;
            diaPicoMaximoLluvia = diaActual;
        }
    }
}
