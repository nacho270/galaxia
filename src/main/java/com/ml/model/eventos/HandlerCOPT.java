package com.ml.model.eventos;

import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

/**
 * Handler para el evento de "Condiciones Optimas de Presion y Temperatura".<br>
 * Se ejecuta solo si los planetas se encuentran alineados entre si pero no con el sol.
 */
public class HandlerCOPT extends HandlerClimaGalaxia {

    @Override
    protected boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return calculadorPosicion.estanAlineados(galaxia.getPlanetas())
                && !calculadorPosicion.estanAlineadosConElSol(galaxia.getPlanetas(), galaxia.getCoordenadasSol());
    }

    @Override
    protected void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(TipoClimaGalaxia.COPT);
    }
}
