package edu.obj.airport;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.obj.Creable;

public class Aerolinea implements Creable {
    public static final ArrayList<String> nombres = new ArrayList<>();
    private final ArrayList<LocalDate> vuelos = new ArrayList<>();
    private final ArrayList<String> aeropuertos = new ArrayList<>();
    private final ArrayList<String> aviones = new ArrayList<>();
    private final ArrayList<String> codigos = new ArrayList<>();
    private final String nombre;
    
    public Aerolinea(String aeropuerto, String nombre) {
        this.nombre = nombre;
        nombres.add(nombre);
        aeropuertos.add(aeropuerto);
    }
    
    public static boolean exists(String nombre) {
        try {
            return nombres.contains(nombre);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void addAeropuerto(String aeropuerto) {
        if (!this.aeropuertos.contains(aeropuerto)) {
            this.aeropuertos.add(aeropuerto);
        }
    }

    public Object[] getAeropuertos() {
        return this.aeropuertos.toArray();
    }

    public void addAvion(String avion) {
        if (!this.aviones.contains(avion)) {
            this.aviones.add(avion);
        }
    }

    public Object[] getAviones() {
        return this.aviones.toArray();
    }

    public boolean existsAeropuerto(String aeropuerto) {
        return this.aeropuertos.contains(aeropuerto);
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
        return "Aerolinea_" + this.nombre;
    }
}
