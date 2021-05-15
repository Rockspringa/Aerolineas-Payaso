package edu.obj.airport;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.obj.Aeropuerto;

public class Vuelo {
    private static final ArrayList<String> codigos = new ArrayList<String>();
    private final int precioBoleto;
    private final Avion avion;
    private final String codigo;
    private final Aeropuerto origen;
    private final Aeropuerto destino;
    private final LocalDate fechaVuelo;

    
    public Vuelo(int precioBoleto, Avion avion, String codigo, Aeropuerto origen, Aeropuerto destino,
            LocalDate fechaVuelo) {
        this.avion = avion;
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fechaVuelo = fechaVuelo;
        this.precioBoleto = precioBoleto;
        Vuelo.codigos.add(codigo);
    }
    
    public static boolean isOcupped(String codigo) {
        for (String cod : Vuelo.codigos) {
            if (cod.equals(codigo)) {
                return false;
            }
        } return true;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public Avion getAvion() {
        return this.avion;
    }

    public Aeropuerto getOrigen() {
        return this.origen;
    }

    public Aeropuerto getDestino() {
        return this.destino;
    }

    public int getPrecioBoleto() {
        return this.precioBoleto;
    }

    public LocalDate getFechaVuelo() {
        return this.fechaVuelo;
    }
}
