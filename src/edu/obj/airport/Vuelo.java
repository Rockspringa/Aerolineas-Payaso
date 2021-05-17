package edu.obj.airport;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.obj.*;

public class Vuelo implements Creable {
    private static final ArrayList<Integer> codigos = new ArrayList<Integer>();
    private final int precioBoleto;
    private final Avion avion;
    private final int codigo;
    private final Aeropuerto origen;
    private final Aeropuerto destino;
    private final LocalDate fechaVuelo;

    
    public Vuelo(int precioBoleto, Avion avion, int codigo, Aeropuerto origen, Aeropuerto destino,
            LocalDate fechaVuelo) {
        this.avion = avion;
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fechaVuelo = fechaVuelo;
        this.precioBoleto = precioBoleto;
        Vuelo.codigos.add(codigo);
    }
    
    public static boolean exists(Integer cod) {
        return codigos.contains(cod);
    }

    public static boolean exists(int cod) {
        return exists(Integer.valueOf(cod));
    }
    
    public int getCodigo() {
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

    @Override
    public String getFilename() {
        return "Vuelo_" + this.codigo;
    }
}
