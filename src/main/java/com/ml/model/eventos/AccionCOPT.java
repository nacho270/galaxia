package com.ml.model.eventos;

import com.ml.model.EventoGalaxia;
import com.ml.model.Galaxia;
import com.ml.model.posicionamiento.common.CalculadorPosicion;

// 3) Alineados sin incluir sol
public class AccionCOPT implements AccionEventoGalaxia {

    @Override
    public boolean aplica(final Galaxia galaxia, final CalculadorPosicion<?> calculadorPosicion) {
        return calculadorPosicion.estanAlineados(galaxia.getPlanetas())
                        && !calculadorPosicion.estanAlineadosConElSol(galaxia.getPlanetas(), galaxia.getCoordenadasSol());
    }

    @Override
    public void computar(final Galaxia galaxia) {
        galaxia.sumarEvento(EventoGalaxia.COPT);
    }
}
