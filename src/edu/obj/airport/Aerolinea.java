package edu.obj.airport;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.obj.Creable;

public class Aerolinea implements Creable {
    public static final ArrayList<String> nombres = new ArrayList<>();
    private final ArrayList<LocalDate> vuelos = new ArrayList<>();
    private final ArrayList<String> codigos = new ArrayList<>();
    private static final ArrayList<String> aeropuertos = new ArrayList<>();
    private final String aeropuerto;
    private final String nombre;
    
    public Aerolinea(String aeropuerto, String nombre) {
        this.aeropuerto = aeropuerto;
        this.nombre = nombre;
        nombres.add(nombre);
        aeropuertos.add(aeropuerto);
    }
    
    public static boolean exists(String aeropuerto, String nombre) {
        try {
            return nombres.contains(nombre) && aeropuertos.get(nombres.indexOf(nombre)).equals(aeropuerto);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public String getAeropuerto() {
        return this.aeropuerto;
    }

    public ArrayList<LocalDate> getVuelos() {
        return this.vuelos;
    }

    public ArrayList<String> getCodigos() {
        return this.codigos;
    }
    public void addVuelo(String codigo, LocalDate salida) {
        this.vuelos.add(salida);
        this.codigos.add(codigo);
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
