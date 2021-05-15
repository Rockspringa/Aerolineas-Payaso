package edu.obj.airport;

public class Aerolinea {
    private final String aeropuerto;
    private final String nombre;
    
    public Aerolinea(String aeropuerto, String nombre) {
        this.aeropuerto = aeropuerto;
        this.nombre = nombre;
    }

    public String getAeropuerto() {
        return this.aeropuerto;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
