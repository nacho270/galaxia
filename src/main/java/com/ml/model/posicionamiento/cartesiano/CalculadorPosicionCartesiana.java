package com.ml.model.posicionamiento.cartesiano;

import java.util.List;

import com.ml.model.Planeta;
import com.ml.model.posicionamiento.common.CalculadorPosicion;
import com.ml.model.posicionamiento.common.CoordenadaBidimensional;

public class CalculadorPosicionCartesiana implements CalculadorPosicion<CoordenadaCartesiana> {

    @Override
    public CoordenadaCartesiana calcularPosicion(final int dia, final short velocidadAngular, final int distanciaAlSol) {
        int distanciaRecorrida = (dia * velocidadAngular);
        if (distanciaRecorrida < 0) {
            distanciaRecorrida = 360 - Math.abs(distanciaRecorrida % 360);
        }
        // final int vueltas = distanciaRecorrida / 360;
        final int angulo = distanciaRecorrida % 360;
        // return new CoordenadaRectangular(round(distanciaAlSol *
        // Math.cos(Math.toRadians(angulo)), 2),
        // round(distanciaAlSol * Math.sin(Math.toRadians(angulo)), 2));

        // trunco a int para simplificar las coincidencias de alineacion
        return new CoordenadaCartesiana((int) (distanciaAlSol * Math.cos(Math.toRadians(angulo))),
                        (int) (distanciaAlSol * Math.sin(Math.toRadians(angulo))));
    }

    @Override
    public CoordenadaCartesiana crearCoordenada(final int x, final int y) {
        return new CoordenadaCartesiana(x, y);
    }

    @Override
    public boolean estanAlineadosConElSol(final List<Planeta> planetas, final CoordenadaBidimensional coordenadasSol) {
        final boolean alineadosSinSol = estanAlineados(planetas);
        if (!alineadosSinSol) {
            return false;
        }
        // si estan alineados, tomo 2 puntos de la lista y me fijo estan
        // alineados con el punto (0,0)
        return esRecta(planetas.get(0).getPosicion(), planetas.get(1).getPosicion(), coordenadasSol);
    }

    @Override
    public boolean estanAlineados(final List<Planeta> planetas) {
        boolean esRecta = true;
        for (int i = 0; i < planetas.size() - 2 && esRecta == true; i++) {
            esRecta &= esRecta(planetas.get(i).getPosicion(), planetas.get(i + 1).getPosicion(), planetas.get(i + 2).getPosicion());
        }
        return esRecta;
    }

    private boolean esRecta(final CoordenadaBidimensional coordP1, final CoordenadaBidimensional coordP2,
                    final CoordenadaBidimensional coordP3) {
        // (y2 - y1) * (x3 - x2) = (y3 - y2) * (x2 - x1)
        return ((coordP2.getY() - coordP1.getY()) * (coordP3.getX() - coordP2.getX())) == ((coordP3.getY() - coordP2.getY())
                        * (coordP2.getX() - coordP1.getX()));
    }

    // private double round(double value, int places) {
    // if (places < 0) {
    // throw new IllegalArgumentException();
    // }
    //
    // final long factor = (long) Math.pow(10, places);
    // value = value * factor;
    // final long tmp = Math.round(value);
    // return (double) tmp / factor;
    // }

    /**
     * @see http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
     */
    @Override
    public boolean distribucionPlanetasContieneSol(final List<Planeta> planetas, final CoordenadaBidimensional coordenadasSol) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = planetas.size() - 1; i < planetas.size(); j = i++) {
            final CoordenadaBidimensional posicionPlanetaI = planetas.get(i).getPosicion();
            final CoordenadaBidimensional posicionPlanetaJ = planetas.get(j).getPosicion();
            final double coordenadaSolY = coordenadasSol.getY();
            final double coordenadaSolX = coordenadasSol.getX();
            final double coordPlanetaJX = posicionPlanetaJ.getX();
            final double coordPlanetaJY = posicionPlanetaJ.getY();
            final double coordPlanetaIX = posicionPlanetaI.getX();
            final double coordPlanetaIY = posicionPlanetaI.getY();
            final double limiteDerecho = (coordPlanetaJX - coordPlanetaIX) * (coordenadaSolY - coordPlanetaIY)
                            / (coordPlanetaJY - coordPlanetaIY) + coordPlanetaIX;

            // si el sol de encuentra dentro de la recta Y de los 2 planetas que
            // estoy tomando
            final boolean puntoDentroDelRangoY = (coordPlanetaIY > coordenadaSolY) != (coordPlanetaJY > coordenadaSolY);
            final boolean solUbicadoAIzquierda = coordenadaSolX < limiteDerecho;
            if (puntoDentroDelRangoY && solUbicadoAIzquierda) {
                result = !result;
            }
        }
        return result;
    }
}
