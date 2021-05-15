package edu.obj.items;

import edu.obj.airport.*;

public class Reservacion {
    private final int pasajero;
    private final Vuelo vuelo;
    private final int tarjeta;
    private final int numAsiento;
    
    public Reservacion(int pasajero, Vuelo vuelo, int tarjeta, int numAsiento) {
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.tarjeta = tarjeta;
        this.numAsiento = numAsiento;
    }

    public int getPasajero() {
        return this.pasajero;
    }

    public Vuelo getVuelo() {
        return this.vuelo;
    }

    public int getTarjeta() {
        return this.tarjeta;
    }

    public int getNumAsiento() {
        return this.numAsiento;
    }
}
