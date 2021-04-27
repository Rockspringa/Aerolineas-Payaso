package edu.obj.airport;

import edu.obj.Aeropuerto;

public class Aerolinea {
    private final Aeropuerto aeropuerto;
    private final String nombre;
    
    public Aerolinea(Aeropuerto aeropuerto, String nombre) {
        this.aeropuerto = aeropuerto;
        this.nombre = nombre;
    }

    public String getAeropuerto() {
        return aeropuerto.getNombre();
    }

    public String getNombre() {
        return nombre;
    }
}
