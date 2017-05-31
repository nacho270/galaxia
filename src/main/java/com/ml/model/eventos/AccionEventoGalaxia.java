package com.ml.model.eventos;

import java.util.List;

import com.ml.model.Galaxia;
import com.ml.model.Planeta;
import com.ml.model.Sol;

public interface AccionEventoGalaxia {

    boolean aplica(final Galaxia galaxia);

    void computar(final Galaxia galaxia);

    default boolean estanAlineados(final List<Planeta> planetas) {
        return false;
    }

    default boolean estanAlineados(final List<Planeta> planetas, final Sol sol) {
        return false;
    }
}
