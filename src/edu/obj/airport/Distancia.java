package edu.obj.airport;

import edu.obj.Aeropuerto;

public class Distancia {
    private final Aeropuerto origen;
    private final Aeropuerto destino;
    private final int millas;

    public Distancia(Aeropuerto origen, Aeropuerto destino, int millas) {
        this.origen = origen;
        this.destino = destino;
        this.millas = millas;
    }

    public Aeropuerto getOrigen() {
        return this.origen;
    }

    public Aeropuerto getDestino() {
        return this.destino;
    }

    public int getMillas() {
        return this.millas;
    }

    @Override
    public String toString() {
        return String.valueOf(this.millas);
    }
}
