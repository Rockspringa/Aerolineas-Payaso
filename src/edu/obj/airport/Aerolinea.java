package edu.obj.airport;

import java.util.ArrayList;

import edu.obj.Creable;

public class Aerolinea implements Creable {
    private static final ArrayList<String> nombres = new ArrayList<>();
    private static final ArrayList<String> aeropuertos = new ArrayList<>();
    private final String aeropuerto;
    private final String nombre;
    
    public Aerolinea(String aeropuerto, String nombre) {
        this.aeropuerto = aeropuerto;
        this.nombre = nombre;
        nombres.add(nombre);
        aeropuertos.add(aeropuerto);
    }
    
    public static boolean exists(String nombre, String aeropuerto) {
        try {
            return nombres.contains(nombre) && aeropuertos.get(nombres.indexOf(nombre)).equals(aeropuerto);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
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

    @Override
    public String getFilename() {
        return "Aerolinea_" + this.nombre + "_" + this.aeropuerto;
    }
}
